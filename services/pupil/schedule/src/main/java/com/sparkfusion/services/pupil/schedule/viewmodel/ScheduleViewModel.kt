package com.sparkfusion.services.pupil.schedule.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.pupil.portschedule.IReadSchedulesUseCase
import com.sparkfusion.portdomainservices.pupil.portschedule.ScheduleModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ScheduleViewModel @Inject constructor(
    private val readSchedulesUseCase: IReadSchedulesUseCase
) : ViewModel() {

    private val _scheduleReadingState =
        MutableStateFlow<ScheduleReadingState>(ScheduleReadingState.Initial)
    internal val scheduleReadingState: StateFlow<ScheduleReadingState> =
        _scheduleReadingState.asStateFlow()

    internal fun readSchedules() {
        if (scheduleReadingState.value is ScheduleReadingState.Success) return
        if (scheduleReadingState.value == ScheduleReadingState.Progress) return

        _scheduleReadingState.update { ScheduleReadingState.Progress }
        viewModelScope.launch {
            readSchedulesUseCase.readSchedules()
                .onSuccess { list ->
                    _scheduleReadingState.update { ScheduleReadingState.Success(list) }
                }
                .onFailure {
                    _scheduleReadingState.update { ScheduleReadingState.Error }
                }
        }
    }

    internal fun clearScheduleReadingState() {
        _scheduleReadingState.update { ScheduleReadingState.Initial }
    }

    internal sealed interface ScheduleReadingState {
        data object Initial : ScheduleReadingState
        data object Error : ScheduleReadingState
        data object Progress : ScheduleReadingState
        data class Success(val schedules: List<ScheduleModel>) : ScheduleReadingState
    }
}
























