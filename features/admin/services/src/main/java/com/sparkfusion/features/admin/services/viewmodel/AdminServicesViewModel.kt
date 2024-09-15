package com.sparkfusion.features.admin.services.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.dispatchers.MainDispatcher
import com.sparkfusion.core.common.viewmodel.DefaultViewModel
import com.sparkfusion.domain.admin.port.portservices.IReadNewsUseCase
import com.sparkfusion.domain.admin.port.portservices.IReadServicesUseCase
import com.sparkfusion.domain.admin.port.portservices.NewsEntity
import com.sparkfusion.domain.admin.port.portservices.ServiceEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

typealias NewsEntities = List<NewsEntity>

@HiltViewModel
class AdminServicesViewModel @Inject constructor(
    private val readServicesUseCase: IReadServicesUseCase,
    private val readNewsUseCase: IReadNewsUseCase,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: MainCoroutineDispatcher
) : DefaultViewModel() {

    val enabledServices: Flow<List<ServiceEntity>>
        get() = readServicesUseCase.enabledServices.flowOn(ioDispatcher)

    private val _news = MutableStateFlow<NewsEntities>(emptyList())
    val news: StateFlow<NewsEntities> = _news.asStateFlow()

    fun loadNewsAnswer() {
        viewModelScope.launch(ioDispatcher) {
            readNewsUseCase.loadNewsAnswer()
                .onSuccess {
                    Log.i("TTTTT", it.joinToString())
                    withContext(mainDispatcher) { _news.value = it }
                }
                .onFailure {
                    Log.i("TTTTT", it.message.toString())
                }
        }
    }

    fun loadNews() {
        viewModelScope.launch(ioDispatcher) {
            try {
                val data = readNewsUseCase.loadNews()
                withContext(mainDispatcher) { _news.value = data }
            } catch (e: Exception) {
                Log.i("TTTTT", e.message.toString())
            }
            Log.i("TTTTT", news.value.joinToString())
        }
    }
}