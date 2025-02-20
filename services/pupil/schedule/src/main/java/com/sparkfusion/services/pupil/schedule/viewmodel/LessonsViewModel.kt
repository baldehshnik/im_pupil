package com.sparkfusion.services.pupil.schedule.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.pupil.portschedule.IReadScheduleWithLessonsUseCase
import com.sparkfusion.portdomainservices.pupil.portschedule.LessonModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LessonsViewModel @Inject constructor(
    private val readScheduleWithLessonsUseCase: IReadScheduleWithLessonsUseCase
) : ViewModel() {

    private val _readingState = MutableStateFlow<ReadingState>(ReadingState.Initial)
    internal val readingState: StateFlow<ReadingState> = _readingState.asStateFlow()

    private val _state = MutableStateFlow(State())
    internal val state: StateFlow<State> = _state.asStateFlow()

    internal fun readLessons(id: Int) {
        if (readingState.value == ReadingState.Progress) return

        _readingState.update { ReadingState.Progress }
        viewModelScope.launch {
            readScheduleWithLessonsUseCase.readScheduleWithLessons(id)
                .onSuccess { model ->
                    val upperWeekLessons = model.lessons.asSequence()
                        .filter { it.weekType == 1 }
                        .groupBy { it.dayofweek }

                    val bottomWeekLessons = model.lessons.asSequence()
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

    internal fun updateCurrentUpperWeekType(value: Int) {
        _state.update { it.copy(currentUpperWeekType = value) }
    }

    internal fun updateCurrentBottomWeekType(value: Int) {
        _state.update { it.copy(currentBottomWeekType = value) }
    }

    internal fun clearReadingState() {
        _readingState.update { ReadingState.Initial }
    }

    internal data class State(
        val currentUpperWeekType: Int = 1,
        val currentBottomWeekType: Int = 1
    )

    internal sealed interface ReadingState {
        data object Initial : ReadingState
        data object Progress : ReadingState
        data object Error : ReadingState
        data class Success(
            val upperWeek: Map<Int, List<LessonModel>>,
            val bottomWeek: Map<Int, List<LessonModel>>
        ) : ReadingState
    }
}























