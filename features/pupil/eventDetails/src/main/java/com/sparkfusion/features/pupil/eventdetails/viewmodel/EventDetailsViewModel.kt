package com.sparkfusion.features.pupil.eventdetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.domain.pupil.port.porteventdetails.model.EventDetailsModel
import com.sparkfusion.domain.pupil.port.porteventdetails.usecase.IEventDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class EventDetailsViewModel @Inject constructor(
    private val readEventDetailsUseCase: IEventDetailsUseCase
): ViewModel() {

    private val _eventState = MutableStateFlow<EventState>(EventState.Initial)
    internal val eventState: StateFlow<EventState> = _eventState.asStateFlow()

    internal fun readEvent(id: Int) {
        if (eventState.value is EventState.Success) return
        if (eventState.value == EventState.Progress) return

        _eventState.update { EventState.Progress }
        viewModelScope.launch {
            readEventDetailsUseCase.readEventById(id)
                .onSuccess { model ->
                    _eventState.update { EventState.Success(model) }
                }
                .onFailure {
                    _eventState.update { EventState.Error }
                }
        }
    }

    internal fun clearEventState() {
        _eventState.update { EventState.Initial }
    }

    internal sealed interface EventState {
        data object Initial : EventState
        data object Error : EventState
        data object Progress : EventState
        data class Success(val data: EventDetailsModel) : EventState
    }
}

























