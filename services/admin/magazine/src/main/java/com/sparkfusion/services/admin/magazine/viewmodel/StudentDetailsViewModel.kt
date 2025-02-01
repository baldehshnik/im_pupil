package com.sparkfusion.services.admin.magazine.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.admin.portmagazine.model.ReadGroupMemberWithPassesModel
import com.sparkfusion.portdomainservices.admin.portmagazine.model.ReadWeekDayPassModel
import com.sparkfusion.portdomainservices.admin.portmagazine.model.UpdatePassStatusModel
import com.sparkfusion.portdomainservices.admin.portmagazine.usecase.IReadPassOfGroupMemberUseCase
import com.sparkfusion.portdomainservices.admin.portmagazine.usecase.IReadWeekStatisticsUseCase
import com.sparkfusion.portdomainservices.admin.portmagazine.usecase.IUpdatePassOfGroupMemberUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class StudentDetailsViewModel @Inject constructor(
    private val readPassOfGroupMemberUseCase: IReadPassOfGroupMemberUseCase,
    private val readWeekStatisticsUseCase: IReadWeekStatisticsUseCase,
    private val updatePassOfGroupMemberUseCase: IUpdatePassOfGroupMemberUseCase
) : ViewModel() {

    private val _readingState = MutableStateFlow<ReadingState>(ReadingState.Initial)
    val readingState: StateFlow<ReadingState> = _readingState.asStateFlow()

    private val _readingWeekStatisticsState =
        MutableStateFlow<ReadWeekStatisticsState>(ReadWeekStatisticsState.Initial)
    val readingWeekStatisticsState: StateFlow<ReadWeekStatisticsState> =
        _readingWeekStatisticsState.asStateFlow()

    private val _updatingState = MutableStateFlow<UpdatingState>(UpdatingState.Initial)

    fun readGroupMember(groupMemberId: Int, lessonId: Int) {
        if (readingState.value == ReadingState.Progress) return

        _readingState.update { ReadingState.Progress }
        viewModelScope.launch {
            readPassOfGroupMemberUseCase.readPassOfGroupMember(groupMemberId, lessonId)
                .onSuccess { model ->
                    _readingState.update { ReadingState.Success(model) }
                }
                .onFailure {
                    _readingState.update { ReadingState.Error }
                }
        }
    }

    fun readWeeklyStatistics(groupMemberId: Int) {
        if (readingWeekStatisticsState.value == ReadWeekStatisticsState.Progress) return

        _readingWeekStatisticsState.update { ReadWeekStatisticsState.Progress }
        viewModelScope.launch {
            readWeekStatisticsUseCase.readWeekStatistics(groupMemberId)
                .onSuccess {
                    val data = it.groupBy { model -> model.dayOfWeek }
                    _readingWeekStatisticsState.update { ReadWeekStatisticsState.Success(data) }
                }
                .onFailure {
                    _readingWeekStatisticsState.update { ReadWeekStatisticsState.Error }
                }
        }
    }

    fun updateStatus(newStatus: Int, lessonId: Int) {
        if (readingState.value !is ReadingState.Success) return
        if (_updatingState.value == UpdatingState.Progress) return

        _updatingState.update { UpdatingState.Progress }
        val data = (readingState.value as ReadingState.Success).model
        val oldStatus = data.getPassDto.status

        _readingState.update {
            ReadingState.Success(
                data.copy(getPassDto = data.getPassDto.copy(status = newStatus))
            )
        }
        viewModelScope.launch {
            updatePassOfGroupMemberUseCase.updatePassOfGroupMember(
                UpdatePassStatusModel(
                    data.getPassDto.id,
                    data.getGroupMemberDto.id,
                    lessonId,
                    LocalDate.now(),
                    newStatus
                )
            ).onSuccess {
                _updatingState.update { UpdatingState.Initial }
            }.onFailure {
                _readingState.update {
                    ReadingState.Success(
                        data.copy(getPassDto = data.getPassDto.copy(status = oldStatus))
                    )
                }
                _updatingState.update { UpdatingState.Initial }
            }
        }
    }

    fun clearReadingState() {
        _readingState.update { ReadingState.Initial }
    }

    fun clearReadingWeekStatisticsState() {
        _readingWeekStatisticsState.update { ReadWeekStatisticsState.Initial }
    }

    sealed interface UpdatingState {
        data object Initial : UpdatingState
        data object Progress : UpdatingState
    }

    sealed interface ReadWeekStatisticsState {
        data object Initial : ReadWeekStatisticsState
        data object Error : ReadWeekStatisticsState
        data object Progress : ReadWeekStatisticsState
        data class Success(val data: Map<Int, List<ReadWeekDayPassModel>>) : ReadWeekStatisticsState
    }

    sealed interface ReadingState {
        data object Initial : ReadingState
        data object Error : ReadingState
        data object Progress : ReadingState
        data class Success(val model: ReadGroupMemberWithPassesModel) : ReadingState
    }
}























