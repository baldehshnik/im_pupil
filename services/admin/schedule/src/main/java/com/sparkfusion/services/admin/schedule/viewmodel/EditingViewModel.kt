package com.sparkfusion.services.admin.schedule.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.admin.portschedule.model.UpdateLessonModel
import com.sparkfusion.portdomainservices.admin.portschedule.model.UpdateScheduleModel
import com.sparkfusion.portdomainservices.admin.portschedule.usecase.IReadScheduleWithLessonsUseCase
import com.sparkfusion.portdomainservices.admin.portschedule.usecase.IUpdateScheduleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalTime
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class EditingViewModel @Inject constructor(
    private val updateScheduleUseCase: IUpdateScheduleUseCase,
    private val readScheduleWithLessonsUseCase: IReadScheduleWithLessonsUseCase
) : ViewModel() {

    var currentUpdatingLesson: UpdateLessonModel? = null

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    private val _editingState = MutableStateFlow<EditingState>(EditingState.Initial)
    val editingState: StateFlow<EditingState> = _editingState.asStateFlow()

    private val _checkState = MutableStateFlow<CheckState>(CheckState.Initial)
    val checkState: StateFlow<CheckState> = _checkState.asStateFlow()

    private val _lessonEditingState = MutableStateFlow(LessonEditingState())
    val lessonEditingState: StateFlow<LessonEditingState> = _lessonEditingState.asStateFlow()

    private val _lessonCheckState = MutableStateFlow<LessonCheckState>(LessonCheckState.Initial)
    val lessonCheckState: StateFlow<LessonCheckState> = _lessonCheckState.asStateFlow()

    private val _readingState = MutableStateFlow<ReadingState>(ReadingState.Initial)
    val readingState: StateFlow<ReadingState> = _readingState.asStateFlow()

    private val _lessonReadingState = MutableStateFlow<LessonReadingState>(LessonReadingState.Initial)
    val lessonReadingState: StateFlow<LessonReadingState> = _lessonReadingState.asStateFlow()

    fun readLesson() {
        if (currentUpdatingLesson == null) {
            _lessonReadingState.update { LessonReadingState.Success }
            return
        } else if (currentUpdatingLesson!!.id == null) {
            _lessonReadingState.update { LessonReadingState.Success }
            return
        }

        val model = currentUpdatingLesson!!
        _lessonEditingState.update {
            it.copy(
                id = model.id,
                name = model.name,
                begin = localTimeToCalendar(model.start),
                end = localTimeToCalendar(model.end),
                teacher = model.teacher,
                type = model.type,
                audience = model.audience
            )
        }
        _lessonReadingState.update { LessonReadingState.Success }
    }

    fun read(id: Int) {
        if (readingState.value == ReadingState.Progress) return

        _readingState.update { ReadingState.Progress }
        viewModelScope.launch {
            readScheduleWithLessonsUseCase.readScheduleWithLessons(id)
                .onSuccess { model ->
                    val upperWeek = model.lessons.asSequence()
                        .filter { it.weekType == 1 }
                        .map { lesson ->
                            UpdateLessonModel(
                                lesson.id,
                                lesson.name,
                                lesson.start,
                                lesson.end,
                                lesson.teacher,
                                lesson.audience,
                                lesson.type,
                                lesson.dayofweek,
                                lesson.weekType
                            )
                        }
                        .groupBy { it.dayofweek }

                    val bottomWeek = model.lessons.asSequence()
                        .filter { it.weekType == 2 }
                        .map { lesson ->
                            UpdateLessonModel(
                                lesson.id,
                                lesson.name,
                                lesson.start,
                                lesson.end,
                                lesson.teacher,
                                lesson.audience,
                                lesson.type,
                                lesson.dayofweek,
                                lesson.weekType
                            )
                        }
                        .groupBy { it.dayofweek }

                    _state.update {
                        it.copy(
                            id = model.id,
                            name = model.name,
                            begin = instantToCalendar(model.startDate),
                            end = instantToCalendar(model.finishDate),
                            weekType = model.startType,
                            upperWeek = upperWeek,
                            bottomWeek = bottomWeek,
                            type = model.type
                        )
                    }
                    _readingState.update { ReadingState.Success }
                }
                .onFailure {
                    Log.d("TAGTAG", it.message.toString())
                    _readingState.update { ReadingState.Error }
                }
        }
    }

    fun save(groupId: Int, scheduleId: Int) {
        if (readingState.value != ReadingState.Success) return
        if (editingState.value == EditingState.Progress) return

        if (state.value.name.isEmpty()) {
            _checkState.update { CheckState.EmptyName }
            return
        }

        _editingState.update { EditingState.Progress }
        viewModelScope.launch {
            val upper = state.value.upperWeek.values.flatten().toMutableList()
            val bottom = state.value.bottomWeek.values.flatten()
            upper += bottom

            val model = UpdateScheduleModel(
                groupId = groupId,
                id = scheduleId,
                name = state.value.name,
                finishDate = state.value.end.toInstant(),
                startDate = state.value.begin.toInstant(),
                startType = state.value.weekType,
                lessons = upper,
                type = state.value.type
            )
            Log.d("TAGTAG", model.toString())
            updateScheduleUseCase.updateSchedule(model)
                .onSuccess {
                    _editingState.update { EditingState.Success }
                }
                .onFailure {
                    Log.d("TAGTAG", it.message.toString())
                    _editingState.update { EditingState.Error }
                }
        }
    }

    fun saveLesson(weekType: Int, dayOfWeek: Int) {
        if (lessonEditingState.value.name.isEmpty()) {
            _lessonCheckState.update { LessonCheckState.NameIsEmpty }
            return
        }

        if (lessonEditingState.value.teacher.isEmpty()) {
            _lessonCheckState.update { LessonCheckState.TeacherIsEmpty }
            return
        }

        if (lessonEditingState.value.audience.isEmpty()) {
            _lessonCheckState.update { LessonCheckState.AudienceNotSelected }
            return
        }

        val model = UpdateLessonModel(
            id = lessonEditingState.value.id,
            name = lessonEditingState.value.name,
            start = calendarToLocalTime(lessonEditingState.value.begin),
            end = calendarToLocalTime(lessonEditingState.value.end),
            teacher = lessonEditingState.value.teacher,
            audience = lessonEditingState.value.audience,
            type = lessonEditingState.value.type,
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
        _lessonEditingState.update { LessonEditingState() }
    }

    fun clearLessonEditingState() {
        _lessonEditingState.update { LessonEditingState() }
    }

    fun clearLessonCheckState() {
        _lessonCheckState.update { LessonCheckState.Initial }
    }

    fun clearReadingState() {
        _readingState.update { ReadingState.Initial }
    }

    fun clearCheckState() {
        _checkState.update { CheckState.Initial }
    }

    fun clearEditingState() {
        _editingState.update { EditingState.Initial }
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

    fun deleteUpperWeekItem(model: UpdateLessonModel) {
        val key = state.value.currentUpperWeekType
        val items = state.value.upperWeek[key]?.toMutableSet() ?: mutableListOf()
        items.remove(model)

        val mutableMap = state.value.upperWeek.toMutableMap()
        mutableMap[key] = items.toList()
        _state.update { it.copy(upperWeek = mutableMap.toMap()) }
    }

    fun deleteBottomWeekItem(model: UpdateLessonModel) {
        val key = state.value.currentBottomWeekType
        val items = state.value.bottomWeek[key]?.toMutableSet() ?: mutableListOf()
        items.remove(model)

        val mutableMap = state.value.bottomWeek.toMutableMap()
        mutableMap[key] = items.toList()
        _state.update { it.copy(bottomWeek = mutableMap.toMap()) }
    }

    fun updateLessonName(value: String) {
        _lessonEditingState.update { it.copy(name = value) }
    }

    fun updateLessonTeacher(value: String) {
        _lessonEditingState.update { it.copy(teacher = value) }
    }

    fun updateLessonAudience(value: String) {
        _lessonEditingState.update { it.copy(audience = value) }
    }

    fun updateLessonType(value: Int) {
        _lessonEditingState.update { it.copy(type = value) }
    }

    private fun calendarToLocalTime(calendar: Calendar): LocalTime {
        return LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE))
    }

    private fun localTimeToCalendar(localTime: LocalTime): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, localTime.hour)
        calendar.set(Calendar.MINUTE, localTime.minute)
        calendar.set(Calendar.SECOND, localTime.second)
        return calendar
    }

    private fun instantToCalendar(instant: Instant): Calendar {
        val date = Date.from(instant)
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar
    }

    sealed interface ReadingState {
        data object Initial : ReadingState
        data object Progress : ReadingState
        data object Error : ReadingState
        data object Success : ReadingState
    }

    sealed interface CheckState {
        data object Initial : CheckState
        data object EmptyName : CheckState
    }

    sealed interface EditingState {
        data object Initial : EditingState
        data object Error : EditingState
        data object Progress : EditingState
        data object Success : EditingState
    }

    sealed interface LessonReadingState {
        data object Initial : LessonReadingState
        data object Success : LessonReadingState
    }

    sealed interface LessonCheckState {
        data object Initial : LessonCheckState
        data object NameIsEmpty : LessonCheckState
        data object TeacherIsEmpty : LessonCheckState
        data object AudienceNotSelected : LessonCheckState
        data object Success : LessonCheckState
    }

    data class LessonEditingState(
        val id: Int? = null,
        val name: String = "",
        val begin: Calendar = Calendar.getInstance(),
        val end: Calendar = Calendar.getInstance(),
        val teacher: String = "",
        val type: Int = 1,
        val audience: String = ""
    )

    data class State(
        val id: Int = -1,
        val name: String = "",
        val begin: Calendar = Calendar.getInstance(),
        val end: Calendar = Calendar.getInstance(),
        val weekType: Int = 1,
        val type: Int = 0,
        val upperWeek: Map<Int, List<UpdateLessonModel>> = emptyMap(),
        val bottomWeek: Map<Int, List<UpdateLessonModel>> = emptyMap(),
        val currentUpperWeekType: Int = 1,
        val currentBottomWeekType: Int = 1
    )
}
























