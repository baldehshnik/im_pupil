package com.sparkfusion.services.pupil.magazine.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.AccountModel
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.LessonModel
import com.sparkfusion.portdomainservices.pupil.portmagazine.usecase.IReadPupilAccountUseCase
import com.sparkfusion.portdomainservices.pupil.portmagazine.usecase.IReadTodayScheduleUseCase
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class TodayScheduleViewModel @Inject constructor(
    private val readTodayScheduleUseCase: Lazy<IReadTodayScheduleUseCase>,
    private val readAccountUseCase: Lazy<IReadPupilAccountUseCase>
) : ViewModel() {

    private val _scheduleReadingState =
        MutableStateFlow<ScheduleReadingState>(ScheduleReadingState.Initial)
    internal val scheduleReadingState: StateFlow<ScheduleReadingState> =
        _scheduleReadingState.asStateFlow()

    private val _accountReadingState =
        MutableStateFlow<AccountReadingState>(AccountReadingState.Initial)
    internal val accountReadingState: StateFlow<AccountReadingState> =
        _accountReadingState.asStateFlow()

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

    internal fun readSchedules() {
        _scheduleReadingState.update { ScheduleReadingState.Progress }
        viewModelScope.launch {
            readTodayScheduleUseCase.get().readTodaySchedule()
                .onSuccess { list ->
                    _scheduleReadingState.update { ScheduleReadingState.Success(list) }
                }
                .onFailure {
                    _scheduleReadingState.update { ScheduleReadingState.Error }
                }
        }
    }

    internal fun clearAccountReadingState() {
        _accountReadingState.update { AccountReadingState.Initial }
    }

    internal fun clearScheduleReadingState() {
        _scheduleReadingState.update { ScheduleReadingState.Initial }
    }

    internal sealed interface AccountReadingState {
        data object Initial : AccountReadingState
        data object Error : AccountReadingState
        data object Progress : AccountReadingState
        data class Success(val model: AccountModel?) : AccountReadingState
    }

    internal sealed interface ScheduleReadingState {
        data object Initial : ScheduleReadingState
        data object Error : ScheduleReadingState
        data object Progress : ScheduleReadingState
        data class Success(val schedules: List<LessonModel>) : ScheduleReadingState
    }
}
























