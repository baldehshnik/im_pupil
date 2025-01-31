package com.sparkfusion.services.admin.schedule.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.admin.portschedule.model.ReadLessonModel
import com.sparkfusion.portdomainservices.admin.portschedule.usecase.IClearScheduleStatusUseCase
import com.sparkfusion.portdomainservices.admin.portschedule.usecase.IMakeScheduleCurrentUseCase
import com.sparkfusion.portdomainservices.admin.portschedule.usecase.IReadLessonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val readLessonsUseCase: IReadLessonsUseCase,
    private val makeScheduleCurrentUseCase: IMakeScheduleCurrentUseCase,
    private val clearScheduleStatusUseCase: IClearScheduleStatusUseCase
) : ViewModel() {

    private val _readingState = MutableStateFlow<ReadingState>(ReadingState.Initial)
    val readingState: StateFlow<ReadingState> = _readingState.asStateFlow()

    private val _updatingStatusState =
        MutableStateFlow<UpdatingStatusState>(UpdatingStatusState.Initial)
    val updatingStatusState: StateFlow<UpdatingStatusState> = _updatingStatusState.asStateFlow()

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    fun updateScheduleStatus(scheduleId: Int) {
        if (state.value.currentStatus == -1) return
        if (updatingStatusState.value == UpdatingStatusState.Progress) return

        _updatingStatusState.update { UpdatingStatusState.Progress }
        viewModelScope.launch {
            if (state.value.currentStatus == 1) {
                clearScheduleStatusUseCase.clearScheduleStatus(scheduleId)
                    .onSuccess {
                        _state.update { it.copy(currentStatus = 0) }
                        _updatingStatusState.update { UpdatingStatusState.Initial }
                    }
                    .onFailure {
                        _updatingStatusState.update { UpdatingStatusState.Error }
                    }
            } else {
                makeScheduleCurrentUseCase.makeScheduleAsACurrent(scheduleId)
                    .onSuccess {
                        _state.update { it.copy(currentStatus = 1) }
                        _updatingStatusState.update { UpdatingStatusState.Initial }
                    }
                    .onFailure {
                        _updatingStatusState.update { UpdatingStatusState.Error }
                    }
            }
        }
    }

    fun readLessons(id: Int) {
        if (readingState.value == ReadingState.Progress) return

        _readingState.update { ReadingState.Progress }
        viewModelScope.launch {
            readLessonsUseCase.readLessonsByScheduleId(id)
                .onSuccess { list ->
                    val upperWeekLessons = list.asSequence()
                        .filter { it.weekType == 1 }
                        .groupBy { it.dayofweek }

                    val bottomWeekLessons = list.asSequence()
                        .filter { it.weekType == 2 }
                        .groupBy { it.dayofweek }

                    _readingState.update {
                        ReadingState.Success(
                            upperWeekLessons,
                            bottomWeekLessons
                        )
                    }
                }
                .onFailure {
                    Log.d("TAGTAG", it.message.toString())
                    _readingState.update { ReadingState.Error }
                }
        }
    }

    fun updateCurrentUpperWeekType(value: Int) {
        _state.update { it.copy(currentUpperWeekType = value) }
    }

    fun updateCurrentBottomWeekType(value: Int) {
        _state.update { it.copy(currentBottomWeekType = value) }
    }

    fun updateStatus(value: Int) {
        _state.update { it.copy(currentStatus = value) }
    }

    fun clearReadingState() {
        _readingState.update { ReadingState.Initial }
    }

    fun clearUpdatingState() {
        _updatingStatusState.update { UpdatingStatusState.Initial }
    }

    data class State(
        val currentUpperWeekType: Int = 1,
        val currentBottomWeekType: Int = 1,
        val currentStatus: Int = -1
    )

    sealed interface UpdatingStatusState {
        data object Initial : UpdatingStatusState
        data object Error : UpdatingStatusState
        data object Progress : UpdatingStatusState
    }

    sealed interface ReadingState {
        data object Initial : ReadingState
        data object Progress : ReadingState
        data object Error : ReadingState
        data class Success(
            val upperWeek: Map<Int, List<ReadLessonModel>>,
            val bottomWeek: Map<Int, List<ReadLessonModel>>
        ) : ReadingState
    }
}



























