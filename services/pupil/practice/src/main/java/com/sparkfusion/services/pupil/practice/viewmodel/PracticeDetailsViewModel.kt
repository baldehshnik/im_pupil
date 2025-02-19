package com.sparkfusion.services.pupil.practice.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.pupil.portpractice.IReadPracticeByIdUseCase
import com.sparkfusion.portdomainservices.pupil.portpractice.PracticeInfoModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class PracticeDetailsViewModel @Inject constructor(
    private val readPracticeByIdUseCase: IReadPracticeByIdUseCase
) : ViewModel() {

    private val _readingState = MutableStateFlow<ReadingState>(ReadingState.Initial)
    internal val readingState: StateFlow<ReadingState> = _readingState.asStateFlow()

    internal fun read(id: Int) {
        if (readingState.value is ReadingState.Success) return
        if (readingState.value == ReadingState.Progress) return

        _readingState.update { ReadingState.Progress }
        Log.d("TAGTAG", "ID - $id")
        viewModelScope.launch {
            readPracticeByIdUseCase.readPracticeById(id)
                .onSuccess { model ->
                    _readingState.update { ReadingState.Success(model) }
                }
                .onFailure {
                    Log.d("TAGTAG", it.message.toString())
                    _readingState.update { ReadingState.Error }
                }
        }
    }

    internal fun clearReadingState() {
        _readingState.update { ReadingState.Initial }
    }

    internal sealed interface ReadingState {
        data object Initial : ReadingState
        data object Progress : ReadingState
        data object Error : ReadingState
        data class Success(val model: PracticeInfoModel) : ReadingState
    }
}




















