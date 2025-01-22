package com.sparkfusion.features.admin.post.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.domain.admin.port.portpost.IDeleteInstitutionEventUseCase
import com.sparkfusion.domain.admin.port.portpost.IReadInstitutionEventUseCase
import com.sparkfusion.domain.admin.port.portpost.InstitutionEventModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewingViewModel @Inject constructor(
    private val readInstitutionEventUseCase: IReadInstitutionEventUseCase,
    private val deleteInstitutionEventUseCase: IDeleteInstitutionEventUseCase
) : ViewModel() {

    private val _eventState = MutableStateFlow<EventState>(EventState.Initial)
    val eventState: StateFlow<EventState> = _eventState.asStateFlow()

    private val _deletingState = MutableStateFlow<DeletingState>(DeletingState.Initial)
    val deletingState: StateFlow<DeletingState> = _deletingState.asStateFlow()

    fun readEvent(id: Int) {
        if (eventState.value == EventState.Progress) return

        _eventState.update { EventState.Progress }
        viewModelScope.launch {
            readInstitutionEventUseCase.readInstitutionEvent(id)
                .onSuccess { model ->
                    _eventState.update { EventState.Success(model) }
                }
                .onFailure {
                    _eventState.update { EventState.Error }
                }
        }
    }

    fun deleteEvent(id: Int) {
        if (deletingState.value == DeletingState.Progress) return

        _deletingState.update { DeletingState.Progress }
        viewModelScope.launch {
            deleteInstitutionEventUseCase.deleteInstitutionEventById(id)
                .onSuccess {
                    _deletingState.update { DeletingState.Success }
                }
                .onFailure {
                    _deletingState.update { DeletingState.Error }
                }
        }
    }

    fun clearEventState() {
        _eventState.update { EventState.Initial }
    }

    sealed interface DeletingState {
        data object Initial : DeletingState
        data object Error : DeletingState
        data object Progress : DeletingState
        data object Success : DeletingState
    }

    sealed interface EventState {
        data object Initial : EventState
        data object Error : EventState
        data object Progress : EventState
        data class Success(val data: InstitutionEventModel) : EventState
    }
}





























