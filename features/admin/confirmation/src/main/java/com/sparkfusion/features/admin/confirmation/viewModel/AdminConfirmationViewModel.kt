package com.sparkfusion.features.admin.confirmation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.domain.admin.port.portconfirmation.AdminModel
import com.sparkfusion.domain.admin.port.portconfirmation.IConfirmAdminUseCase
import com.sparkfusion.domain.admin.port.portconfirmation.IReadUnconfirmedAdminsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminConfirmationViewModel @Inject constructor(
    private val readUnconfirmedAdminsUseCase: IReadUnconfirmedAdminsUseCase,
    private val confirmAdminUseCase: IConfirmAdminUseCase
) : ViewModel() {

    private val _readingState = MutableStateFlow<ReadingState>(ReadingState.Initial)
    val readingState: StateFlow<ReadingState> = _readingState.asStateFlow()

    private val _confirmState = MutableStateFlow<ConfirmState>(ConfirmState.Initial)
    val confirmState: StateFlow<ConfirmState> = _confirmState.asStateFlow()

    fun readUnconfirmedAdmins() {
        if (readingState.value == ReadingState.Progress) return

        _readingState.update { ReadingState.Progress }
        viewModelScope.launch {
            readUnconfirmedAdminsUseCase.readUnconfirmedAdmins()
                .onSuccess { list ->
                    Log.d("TAGTAG", "MY LIST - " + list.joinToString())
                    _readingState.update { ReadingState.Success(list) }
                }
                .onFailure {
                    Log.d("TAGTAG", "EXCEXCECXCECXEC - ${it.message}")
                    _readingState.update { ReadingState.Error }
                }
        }
    }

    fun confirmAdmin(id: Int) {
        if (confirmState.value == ConfirmState.Progress) return

        _confirmState.update { ConfirmState.Progress }
        viewModelScope.launch {
            confirmAdminUseCase.confirmAdmin(id)
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
        data class Success(val data: List<AdminModel>) : ReadingState
    }
}

































