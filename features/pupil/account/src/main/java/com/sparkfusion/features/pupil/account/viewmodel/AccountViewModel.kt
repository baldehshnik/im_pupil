package com.sparkfusion.features.pupil.account.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.domain.pupil.port.portaccount.model.ReadPupilAccountModel
import com.sparkfusion.domain.pupil.port.portaccount.usecase.IReadPupilAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AccountViewModel @Inject constructor(
    private val readAccountUseCase: IReadPupilAccountUseCase
): ViewModel() {

    private val _readingState = MutableStateFlow<ReadingState>(ReadingState.Initial)
    internal val readingState: StateFlow<ReadingState> = _readingState.asStateFlow()

    internal fun read() {
        if (readingState.value is ReadingState.Success) return
        if (readingState.value == ReadingState.Progress) return

        _readingState.update { ReadingState.Progress }
        viewModelScope.launch {
            readAccountUseCase.readPupilAccount()
                .onSuccess { model ->
                    _readingState.update { ReadingState.Success(model) }
                }
                .onFailure {
                    Log.d("TAGTAG", it.message.toString() + "\n" + it.stackTrace)
                    _readingState.update { ReadingState.Error }
                }
        }
    }

    internal sealed interface ReadingState {
        data object Initial : ReadingState
        data object Progress : ReadingState
        data object Error : ReadingState
        data class Success(val model: ReadPupilAccountModel?) : ReadingState
    }
}




















