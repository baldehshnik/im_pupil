package com.sparkfusion.features.admin.services.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.sparkfusion.core.common.dispatchers.Dispatchers
import com.sparkfusion.core.common.exception.NetworkException
import com.sparkfusion.core.common.viewmodel.DefaultViewModel
import com.sparkfusion.core.resource.animation.AfterNavigationAnimationDelayMillis
import com.sparkfusion.domain.admin.port.portservices.IReadNewsUseCase
import com.sparkfusion.domain.admin.port.portservices.IReadServicesUseCase
import com.sparkfusion.features.admin.services.utils.NewsState
import com.sparkfusion.features.admin.services.utils.ServicesScreenContract
import com.sparkfusion.features.admin.services.utils.ServicesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminServicesViewModel @Inject constructor(
    private val readServicesUseCase: IReadServicesUseCase,
    private val readNewsUseCase: IReadNewsUseCase,
    private val dispatchers: Dispatchers
) : DefaultViewModel<ServicesScreenContract.UIState, ServicesScreenContract.ServicesIntent>() {

    override fun initialState(): ServicesScreenContract.UIState = uiState

    override fun handleIntent(intent: ServicesScreenContract.ServicesIntent) {
        when (intent) {
            ServicesScreenContract.ServicesIntent.ReloadNews -> {
                uiState = uiState.copy(newsState = NewsState.Loading)
                loadNews()
            }

            ServicesScreenContract.ServicesIntent.ReloadServices -> {
                uiState = uiState.copy(servicesState = ServicesState.Loading)
                loadServices()
            }
        }
    }

    private var uiState: ServicesScreenContract.UIState by mutableStateOf(ServicesScreenContract.UIState())

    private fun loadServices(initDelay: Boolean = false) {
        viewModelScope.launch(dispatchers.ioDispatcher) {
            if (initDelay) delay(AfterNavigationAnimationDelayMillis.toLong())
            readServicesUseCase.enabledServices
                .catch {
                    uiState = uiState.copy(servicesState = ServicesState.Error)
                }
                .collect {
                    uiState = uiState.copy(servicesState = ServicesState.Success(it))
                }
        }
    }

    private fun loadNews(initDelay: Boolean = false) {
        viewModelScope.launch(dispatchers.ioDispatcher) {
            if (initDelay) delay(AfterNavigationAnimationDelayMillis.toLong())
            readNewsUseCase.loadNews()
                .onSuccess {
                    uiState = uiState.copy(newsState = NewsState.Success(it))
                }
                .onFailure { exception ->
                    uiState = uiState.copy(
                        newsState = when (exception) {
                            is NetworkException -> NewsState.ConnectionError
                            else -> NewsState.Error
                        }
                    )
                }
        }
    }

    init {
        loadServices(true)
        loadNews(true)
    }
}