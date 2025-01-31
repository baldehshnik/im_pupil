package com.sparkfusion.services.admin.schedule.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.admin.portschedule.model.AddLessonModel
import com.sparkfusion.portdomainservices.admin.portschedule.model.AddScheduleModel
import com.sparkfusion.portdomainservices.admin.portschedule.usecase.ICreateScheduleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class AddingViewModel @Inject constructor(
    private val addScheduleUseCase: ICreateScheduleUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    private val _addingState = MutableStateFlow<AddingState>(AddingState.Initial)
    val addingState: StateFlow<AddingState> = _addingState.asStateFlow()

    private val _checkState = MutableStateFlow<CheckState>(CheckState.Initial)
    val checkState: StateFlow<CheckState> = _checkState.asStateFlow()

    private val _lessonAddingState = MutableStateFlow(LessonAddingState())
    val lessonAddingState: StateFlow<LessonAddingState> = _lessonAddingState.asStateFlow()

    private val _lessonCheckState = MutableStateFlow<LessonCheckState>(LessonCheckState.Initial)
    val lessonCheckState: StateFlow<LessonCheckState> = _lessonCheckState.asStateFlow()

    fun save(groupId: Int) {
        if (addingState.value == AddingState.Progress) return

        if (state.value.name.isEmpty()) {
            _checkState.update { CheckState.EmptyName }
            return
        }

        _addingState.update { AddingState.Progress }
        viewModelScope.launch {
            val upper = state.value.upperWeek.values.flatten().toMutableList()
            val bottom = state.value.bottomWeek.values.flatten()
            upper += bottom

            val model = AddScheduleModel(
                groupId = groupId,
                name = state.value.name,
                finishDate = state.value.end.toInstant(),
                startDate = state.value.begin.toInstant(),
                startType = state.value.weekType,
                lessons = upper
            )
            Log.d("TAGTAG", model.toString())
            addScheduleUseCase.createSchedule(model)
                .onSuccess {
                    _addingState.update { AddingState.Success }
                }
                .onFailure {
                    Log.d("TAGTAG", it.message.toString())
                    _addingState.update { AddingState.Error }
                }
        }
    }

    fun saveLesson(weekType: Int, dayOfWeek: Int) {
        if (lessonAddingState.value.name.isEmpty()) {
            _lessonCheckState.update { LessonCheckState.NameIsEmpty }
            return
        }

        if (lessonAddingState.value.teacher.isEmpty()) {
            _lessonCheckState.update { LessonCheckState.TeacherIsEmpty }
            return
        }

        if (lessonAddingState.value.audience.isEmpty()) {
            _lessonCheckState.update { LessonCheckState.AudienceNotSelected }
            return
        }

        val model = AddLessonModel(
            name = lessonAddingState.value.name,
            start = calendarToLocalTime(lessonAddingState.value.begin),
            end = calendarToLocalTime(lessonAddingState.value.end),
            teacher = lessonAddingState.value.teacher,
            audience = lessonAddingState.value.audience,
            type = lessonAddingState.value.type,
            dayofweek = dayOfWeek,
            weekType = weekType
        )

        if (weekType == 1) {
            val list = state.value.upperWeek[dayOfWeek]?.toMutableList() ?: mutableListOf()
            list.add(model)

            val map = state.value.upperWeek.toMutableMap()
            map[dayOfWeek] = list.toList()
            _state.update { it.copy(upperWeek = map) }
        } else {
            val list = state.value.bottomWeek[dayOfWeek]?.toMutableList() ?: mutableListOf()
            list.add(model)

            val map = state.value.bottomWeek.toMutableMap()
            map[dayOfWeek] = list.toList()
            _state.update { it.copy(bottomWeek = map) }
        }

        _lessonCheckState.update { LessonCheckState.Success }
        _lessonAddingState.update { LessonAddingState() }
    }

    fun clearLessonCheckState() {
        _lessonCheckState.update { LessonCheckState.Initial }
    }

    fun clearCheckState() {
        _checkState.update { CheckState.Initial }
    }

    fun clearAddingState() {
        _addingState.update { AddingState.Initial }
    }

    fun updateName(value: String) {
        _state.update { it.copy(name = value) }
    }

    fun updateWeekType(value: Int) {
        _state.update { it.copy(weekType = value) }
    }

    fun updateCurrentUpperWeekType(value: Int) {
        _state.update { it.copy(currentUpperWeekType = value) }
    }

    fun updateCurrentBottomWeekType(value: Int) {
        _state.update { it.copy(currentBottomWeekType = value) }
    }

    fun deleteUpperWeekItem(model: AddLessonModel) {
        val key = state.value.currentUpperWeekType
        val items = state.value.upperWeek[key]?.toMutableSet() ?: mutableListOf()
        items.remove(model)

        val mutableMap = state.value.upperWeek.toMutableMap()
        mutableMap[key] = items.toList()
        _state.update { it.copy(upperWeek = mutableMap.toMap()) }
    }

    fun deleteBottomWeekItem(model: AddLessonModel) {
        val key = state.value.currentBottomWeekType
        val items = state.value.bottomWeek[key]?.toMutableSet() ?: mutableListOf()
        items.remove(model)

        val mutableMap = state.value.bottomWeek.toMutableMap()
        mutableMap[key] = items.toList()
        _state.update { it.copy(bottomWeek = mutableMap.toMap()) }
    }

    fun updateLessonName(value: String) {
        _lessonAddingState.update { it.copy(name = value) }
    }

    fun updateLessonTeacher(value: String) {
        _lessonAddingState.update { it.copy(teacher = value) }
    }

    fun updateLessonAudience(value: String) {
        _lessonAddingState.update { it.copy(audience = value) }
    }

    fun updateLessonType(value: Int) {
        _lessonAddingState.update { it.copy(type = value) }
    }

    private fun calendarToLocalTime(calendar: Calendar): LocalTime {
        return LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE))
    }

    sealed interface CheckState {
        data object Initial : CheckState
        data object EmptyName : CheckState
    }

    sealed interface AddingState {
        data object Initial : AddingState
        data object Error : AddingState
        data object Progress : AddingState
        data object Success : AddingState
    }

    sealed interface LessonCheckState {
        data object Initial : LessonCheckState
        data object NameIsEmpty : LessonCheckState
        data object TeacherIsEmpty : LessonCheckState
        data object AudienceNotSelected : LessonCheckState
        data object Success : LessonCheckState
    }

    data class LessonAddingState(
        val name: String = "",
        val begin: Calendar = Calendar.getInstance(),
        val end: Calendar = Calendar.getInstance(),
        val teacher: String = "",
        val type: Int = 1,
        val audience: String = ""
    )

    data class State(
        val name: String = "",
        val begin: Calendar = Calendar.getInstance(),
        val end: Calendar = Calendar.getInstance(),
        val weekType: Int = 1,
        val upperWeek: Map<Int, List<AddLessonModel>> = emptyMap(),
        val bottomWeek: Map<Int, List<AddLessonModel>> = emptyMap(),
        val currentUpperWeekType: Int = 1,
        val currentBottomWeekType: Int = 1
    )
}


























