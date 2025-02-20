package com.sparkfusion.services.pupil.magazine.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.AccountModel
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.GroupMemberWithPassModel
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.UpdatePassStatusModel
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.WeekDayPassModel
import com.sparkfusion.portdomainservices.pupil.portmagazine.usecase.IReadPassOfGroupMemberUseCase
import com.sparkfusion.portdomainservices.pupil.portmagazine.usecase.IReadPupilAccountUseCase
import com.sparkfusion.portdomainservices.pupil.portmagazine.usecase.IReadWeekStatisticsUseCase
import com.sparkfusion.portdomainservices.pupil.portmagazine.usecase.IUpdatePassOfGroupMemberUseCase
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
internal class StudentDetailsViewModel @Inject constructor(
    private val readPassOfGroupMemberUseCase: Lazy<IReadPassOfGroupMemberUseCase>,
    private val readWeekStatisticsUseCase: Lazy<IReadWeekStatisticsUseCase>,
    private val updatePassOfGroupMemberUseCase: Lazy<IUpdatePassOfGroupMemberUseCase>,
    private val readAccountUseCase: Lazy<IReadPupilAccountUseCase>
) : ViewModel() {

    private val _readingState = MutableStateFlow<ReadingState>(ReadingState.Initial)
    val readingState: StateFlow<ReadingState> = _readingState.asStateFlow()

    private val _readingWeekStatisticsState =
        MutableStateFlow<ReadWeekStatisticsState>(ReadWeekStatisticsState.Initial)
    val readingWeekStatisticsState: StateFlow<ReadWeekStatisticsState> =
        _readingWeekStatisticsState.asStateFlow()

    private val _accountReadingState =
        MutableStateFlow<AccountReadingState>(AccountReadingState.Initial)
    internal val accountReadingState: StateFlow<AccountReadingState> =
        _accountReadingState.asStateFlow()

    private val _updatingState = MutableStateFlow<UpdatingState>(UpdatingState.Initial)

    internal fun readAccount() {
        if (accountReadingState.value is AccountReadingState.Success) return
        if (accountReadingState.value == AccountReadingState.Progress) return

        _accountReadingState.update { AccountReadingState.Progress }
        viewModelScope.launch {
            readAccountUseCase.get().readPupilAccount()
                .onSuccess { model ->
                    _accountReadingState.update { AccountReadingState.Success(model) }
                }
                .onFailure {
                    _accountReadingState.update { AccountReadingState.Error }
                }
        }
    }

    fun readGroupMember(groupMemberId: Int, lessonId: Int) {
        if (readingState.value == ReadingState.Progress) return

        _readingState.update { ReadingState.Progress }
        viewModelScope.launch {
            readPassOfGroupMemberUseCase.get().readPassOfGroupMember(
                groupMemberId,
                lessonId,
                LocalDate.now()
            ).onSuccess { model ->
                _readingState.update { ReadingState.Success(model) }
            }.onFailure {
                _readingState.update { ReadingState.Error }
            }
        }
    }

    fun readWeeklyStatistics(groupMemberId: Int) {
        if (readingWeekStatisticsState.value == ReadWeekStatisticsState.Progress) return

        _readingWeekStatisticsState.update { ReadWeekStatisticsState.Progress }
        viewModelScope.launch {
            readWeekStatisticsUseCase.get().readWeekStatistics(groupMemberId)
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
            updatePassOfGroupMemberUseCase.get().updatePassOfGroupMember(
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
                Log.d("TAGTAG", it.message.toString())
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

    internal fun clearAccountReadingState() {
        _accountReadingState.update { AccountReadingState.Initial }
    }

    sealed interface UpdatingState {
        data object Initial : UpdatingState
        data object Progress : UpdatingState
    }

    internal sealed interface AccountReadingState {
        data object Initial : AccountReadingState
        data object Error : AccountReadingState
        data object Progress : AccountReadingState
        data class Success(val model: AccountModel?) : AccountReadingState
    }

    sealed interface ReadWeekStatisticsState {
        data object Initial : ReadWeekStatisticsState
        data object Error : ReadWeekStatisticsState
        data object Progress : ReadWeekStatisticsState
        data class Success(val data: Map<Int, List<WeekDayPassModel>>) : ReadWeekStatisticsState
    }

    sealed interface ReadingState {
        data object Initial : ReadingState
        data object Error : ReadingState
        data object Progress : ReadingState
        data class Success(val model: GroupMemberWithPassModel) : ReadingState
    }
}





















