package com.sparkfusion.features.common.news.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.domain.common.portnews.IReadNewsInfoUseCase
import com.sparkfusion.domain.common.portnews.NewsInfoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val readNewsInfoUseCase: IReadNewsInfoUseCase
) : ViewModel() {

    private val _newsLoadingState = MutableStateFlow<NewsLoadingState>(NewsLoadingState.Initial)
    val newsLoadingState: StateFlow<NewsLoadingState> = _newsLoadingState.asStateFlow()

    fun readNewsInfo(id: Int) {
        _newsLoadingState.update { NewsLoadingState.Progress }
        viewModelScope.launch(ioDispatcher) {
            readNewsInfoUseCase.readNewsInfoById(id)
                .onSuccess { model ->
                    _newsLoadingState.update { NewsLoadingState.Success(model) }
                }
                .onFailure {
                    _newsLoadingState.update { NewsLoadingState.Error }
                }
        }
    }

    sealed interface NewsLoadingState {
        data object Initial : NewsLoadingState
        data object Progress : NewsLoadingState
        data object Error : NewsLoadingState
        data class Success(val data: NewsInfoModel) : NewsLoadingState
    }
}






















