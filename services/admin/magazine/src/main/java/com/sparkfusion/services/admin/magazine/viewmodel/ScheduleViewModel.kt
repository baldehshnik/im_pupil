package com.sparkfusion.services.admin.magazine.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.admin.portmagazine.model.GroupModel
import com.sparkfusion.portdomainservices.admin.portmagazine.model.ReadLessonModel
import com.sparkfusion.portdomainservices.admin.portmagazine.usecase.IReadGroupByNamePartUseCase
import com.sparkfusion.portdomainservices.admin.portmagazine.usecase.IReadTodayScheduleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val readGroupByNamePartUseCase: IReadGroupByNamePartUseCase,
    private val readTodayScheduleUseCase: IReadTodayScheduleUseCase
): ViewModel() {

    private val _groupReadingState = MutableStateFlow<List<GroupModel>>(emptyList())
    val groupReadingState: StateFlow<List<GroupModel>> = _groupReadingState.asStateFlow()

    private val _scheduleReadingState =
        MutableStateFlow<ScheduleReadingState>(ScheduleReadingState.Initial)
    val scheduleReadingState: StateFlow<ScheduleReadingState> = _scheduleReadingState.asStateFlow()

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    fun readGroupsByNamePart() {
        val part = state.value.groupName.trim()
        if (part.isEmpty()) return

        viewModelScope.launch {
            readGroupByNamePartUseCase.readGroupByNamePart(part)
                .onSuccess { list ->
                    Log.d("TAGTAG", list.joinToString())
                    _groupReadingState.update { list }
                }
        }
    }

    private fun readSchedules() {
        if (state.value.groupId == -1) return

        _scheduleReadingState.update { ScheduleReadingState.Progress }
        viewModelScope.launch {
            readTodayScheduleUseCase.readTodaySchedule(state.value.groupId)
                .onSuccess { list ->
                    _scheduleReadingState.update { ScheduleReadingState.Success(list) }
                }
                .onFailure {
                    _scheduleReadingState.update { ScheduleReadingState.Error }
                }
        }
    }

    fun updateGroupId(id: Int) {
        _state.update { it.copy(groupId = id) }
        readSchedules()
    }

    fun updateGroupName(value: String) {
        _state.update { it.copy(groupName = value) }
    }

    fun clearScheduleReadingState() {
        _scheduleReadingState.update { ScheduleReadingState.Initial }
    }

    data class State(
        val groupName: String = "",
        val groupId: Int = -1
    )

    sealed interface ScheduleReadingState {
        data object Initial : ScheduleReadingState
        data object Error : ScheduleReadingState
        data object Progress : ScheduleReadingState
        data class Success(val schedules: List<ReadLessonModel>) : ScheduleReadingState
    }
}



























