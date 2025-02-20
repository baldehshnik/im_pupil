package com.sparkfusion.services.pupil.magazine.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.LessonWithPassStatusModel
import com.sparkfusion.portdomainservices.pupil.portmagazine.usecase.IReadScheduleWithPassesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
internal class HistoryViewModel @Inject constructor(
    private val readScheduleWithPassesUseCase: IReadScheduleWithPassesUseCase
): ViewModel() {

    private val _readingState = MutableStateFlow<ReadingState>(ReadingState.Initial)
    val readingState: StateFlow<ReadingState> = _readingState.asStateFlow()

    fun read(groupMemberId: Int, date: Long) {
        if (readingState.value == ReadingState.Progress) return

        _readingState.update { ReadingState.Progress }
        viewModelScope.launch {
            val instant = Instant.ofEpochMilli(date)
            val localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate()
            readScheduleWithPassesUseCase.readScheduleWithPasses(
                groupMemberId, localDate
            ).onSuccess { list ->
                val data = list.groupBy { it.getPassDto.status }
                _readingState.update { ReadingState.Success(data) }
            }.onFailure {
                Log.d("TAGTAG", it.message.toString())
                _readingState.update { ReadingState.Error }
            }
        }
    }

    fun clearReadingState() {
        _readingState.update { ReadingState.Initial }
    }

    sealed interface ReadingState {
        data object Initial : ReadingState
        data object Progress : ReadingState
        data object Error : ReadingState
        data class Success(val data: Map<Int, List<LessonWithPassStatusModel>>) : ReadingState
    }
}






















