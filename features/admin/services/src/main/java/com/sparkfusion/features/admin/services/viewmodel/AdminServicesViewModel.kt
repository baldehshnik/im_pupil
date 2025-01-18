package com.sparkfusion.features.admin.services.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.core.common.dispatchers.Dispatchers
import com.sparkfusion.core.common.exception.NetworkException
import com.sparkfusion.domain.admin.port.portservices.IReadNewsUseCase
import com.sparkfusion.domain.admin.port.portservices.IReadServicesUseCase
import com.sparkfusion.domain.admin.port.portservices.NewsEntity
import com.sparkfusion.domain.admin.port.portservices.ServiceEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminServicesViewModel @Inject constructor(
    private val readServicesUseCase: IReadServicesUseCase,
    private val readNewsUseCase: IReadNewsUseCase,
    private val dispatchers: Dispatchers
) : ViewModel() {

    private val _serviceState = MutableStateFlow<ServicesState>(ServicesState.Initial)
    val servicesState: StateFlow<ServicesState> = _serviceState.asStateFlow()

    private val _newsState = MutableStateFlow<NewsState>(NewsState.Initial)
    val newsState: StateFlow<NewsState> = _newsState.asStateFlow()

    private fun loadServices() {
        _serviceState.update { ServicesState.Progress }
        viewModelScope.launch(dispatchers.ioDispatcher) {
            readServicesUseCase.enabledServices
                .catch {
                    _serviceState.update { ServicesState.Error }
                }
                .collect { list ->
                    _serviceState.update { ServicesState.Success(list) }
                }
        }
    }

    private fun loadNews() {
        _newsState.update { NewsState.Progress }
        viewModelScope.launch(dispatchers.ioDispatcher) {
            readNewsUseCase.loadNews()
                .onSuccess { list ->
                    _newsState.update { NewsState.Success(list) }
                }
                .onFailure { exception ->
                    _newsState.update {
                        when (exception) {
                            is NetworkException -> NewsState.ConnectionError
                            else -> NewsState.Error
                        }
                    }
                }
        }
    }

    init {
        loadServices()
        loadNews()
    }

    sealed interface NewsState {
        data object Initial : NewsState
        data object Error : NewsState
        data object ConnectionError : NewsState
        data object Progress : NewsState
        data class Success(val data: List<NewsEntity>) : NewsState
    }

    sealed interface ServicesState {
        data object Initial : ServicesState
        data object Error : ServicesState
        data object Progress : ServicesState
        data class Success(val data: List<ServiceEntity>) : ServicesState
    }
}




















