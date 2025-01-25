package com.sparkfusion.features.admin.confirmation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.domain.admin.port.portconfirmation.IConfirmPupilUseCase
import com.sparkfusion.domain.admin.port.portconfirmation.IReadUnconfirmedPupilsUseCase
import com.sparkfusion.domain.admin.port.portconfirmation.PupilModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PupilConfirmationViewModel @Inject constructor(
    private val readUnconfirmedPupilsUseCase: IReadUnconfirmedPupilsUseCase,
    private val confirmPupilUseCase: IConfirmPupilUseCase
) : ViewModel() {

    private val _readingState = MutableStateFlow<ReadingState>(ReadingState.Initial)
    val readingState: StateFlow<ReadingState> = _readingState.asStateFlow()

    private val _confirmState = MutableStateFlow<ConfirmState>(ConfirmState.Initial)
    val confirmState: StateFlow<ConfirmState> = _confirmState.asStateFlow()

    fun readUnconfirmedPupils() {
        if (readingState.value == ReadingState.Progress) return

        _readingState.update { ReadingState.Progress }
        viewModelScope.launch {
            readUnconfirmedPupilsUseCase.readUnconfirmedPupils()
                .onSuccess { list ->
                    _readingState.update { ReadingState.Success(list) }
                }
                .onFailure {
                    _readingState.update { ReadingState.Error }
                }
        }
    }

    fun confirmPupil(id: Int) {
        if (confirmState.value == ConfirmState.Progress) return

        _confirmState.update { ConfirmState.Progress }
        viewModelScope.launch {
            confirmPupilUseCase.confirmPupil(id)
                .onSuccess {
                    val state = readingState.value
                    if (state is ReadingState.Success) {
                        val list = state.data.toMutableList()
                        val deletingAdmin = list.find { it.id == id }
                        list.remove(deletingAdmin)

                        _readingState.update { ReadingState.Initial }
                        _readingState.update { ReadingState.Success(list.toList()) }
                    }
                }
                .onFailure {
                    _confirmState.update { ConfirmState.Error }
                }
        }
    }

    fun clearConfirmState() {
        _confirmState.update { ConfirmState.Initial }
    }

    fun clearReadingState() {
        _readingState.update { ReadingState.Initial }
    }

    sealed interface ConfirmState {
        data object Initial : ConfirmState
        data object Error : ConfirmState
        data object Progress : ConfirmState
    }

    sealed interface ReadingState {
        data object Initial : ReadingState
        data object Error : ReadingState
        data object Progress : ReadingState
        data class Success(val data: List<PupilModel>) : ReadingState
    }
}



























