package com.sparkfusion.features.admin.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.domain.admin.port.porthome.IDeleteInstitutionEventUseCase
import com.sparkfusion.domain.admin.port.porthome.IReadAccountUseCase
import com.sparkfusion.domain.admin.port.porthome.IReadInstitutionEventsUseCase
import com.sparkfusion.domain.admin.port.porthome.InstitutionEventModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val readInstitutionEventsUseCase: IReadInstitutionEventsUseCase,
    private val readAccountUseCase: IReadAccountUseCase,
    private val deleteInstitutionEventUseCase: IDeleteInstitutionEventUseCase
) : ViewModel() {

    private val _institutionEventState =
        MutableStateFlow<InstitutionEventState>(InstitutionEventState.Initial)
    val institutionEventState: StateFlow<InstitutionEventState> =
        _institutionEventState.asStateFlow()

    private val _accountInfoState = MutableStateFlow<AccountInfoState>(AccountInfoState.Initial)
    val accountInfoState: StateFlow<AccountInfoState> = _accountInfoState.asStateFlow()

    private val _deleteEventState = MutableStateFlow<DeleteEventState>(DeleteEventState.Initial)
    val deleteEventState: StateFlow<DeleteEventState> = _deleteEventState.asStateFlow()

    fun deleteInstitutionEvent(id: Int) {
        if (deleteEventState.value == DeleteEventState.Progress) return

        _deleteEventState.update { DeleteEventState.Progress }
        viewModelScope.launch {
            deleteInstitutionEventUseCase.deleteById(id)
                .onSuccess {
                    val eventState = institutionEventState.value
                    if (eventState is InstitutionEventState.Success) {
                        val data = eventState.data.toMutableList()
                        val element = data.find { it.id == id }
                        data.remove(element)
                        _institutionEventState.update {
                            InstitutionEventState.Success(data.toList())
                        }
                    }
                    _deleteEventState.update { DeleteEventState.Success }
                }
                .onFailure {
                    _deleteEventState.update { DeleteEventState.Error }
                }
        }
    }

    fun readEvents() {
        _institutionEventState.update { InstitutionEventState.Progress }
        viewModelScope.launch(ioDispatcher) {
            readInstitutionEventsUseCase.readInstitutionEvents()
                .onSuccess { list ->
                    Log.d("TAGTAG", "list - " + list.joinToString())
                    _institutionEventState.update { InstitutionEventState.Success(list) }
                }
                .onFailure {
                    Log.d("TAGTAG", it.message.toString())
                    _institutionEventState.update { InstitutionEventState.Error }
                }
        }
    }

    fun readAccountInfo() {
        viewModelScope.launch(ioDispatcher) {
            readAccountUseCase.readAccount()
                .onSuccess { model ->
                    _accountInfoState.update { AccountInfoState.Success(model.firstname) }
                }
                .onFailure {
                    _accountInfoState.update { AccountInfoState.Success("") }
                }
        }
    }

    sealed interface DeleteEventState {
        data object Initial : DeleteEventState
        data object Progress : DeleteEventState
        data object Error : DeleteEventState
        data object Success : DeleteEventState
    }

    sealed interface AccountInfoState {
        data object Initial : AccountInfoState
        data class Success(val name: String) : AccountInfoState
    }

    sealed interface InstitutionEventState {
        data object Initial : InstitutionEventState
        data object Progress : InstitutionEventState
        data object Error : InstitutionEventState
        data class Success(val data: List<InstitutionEventModel>) : InstitutionEventState
    }
}


















