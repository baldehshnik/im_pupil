package com.sparkfusion.services.admin.statistics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.admin.portstatistics.model.ReadGroupMemberModel
import com.sparkfusion.portdomainservices.admin.portstatistics.usecase.IReadGroupMemberPassesPerMonthUseCase
import com.sparkfusion.portdomainservices.admin.portstatistics.usecase.IReadGroupMemberPassesPerSemesterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentStatisticsViewModel @Inject constructor(
    private val readGroupMemberPassesPerMonthUseCase: IReadGroupMemberPassesPerMonthUseCase,
    private val readGroupMemberPassesPerSemesterUseCase: IReadGroupMemberPassesPerSemesterUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    private val _monthStatisticsState =
        MutableStateFlow<MonthStatisticsState>(MonthStatisticsState.Initial)
    val monthStatisticsState: StateFlow<MonthStatisticsState> = _monthStatisticsState.asStateFlow()

    private val _semesterStatisticsState =
        MutableStateFlow<SemesterStatisticsState>(SemesterStatisticsState.Initial)
    val semesterStatisticsState: StateFlow<SemesterStatisticsState> = _semesterStatisticsState.asStateFlow()

    fun readMonthStatistics() {
        val model = state.value.student ?: return
        if (monthStatisticsState.value == MonthStatisticsState.Progress) return

        _monthStatisticsState.update { MonthStatisticsState.Progress }
        viewModelScope.launch {
            readGroupMemberPassesPerMonthUseCase.readGroupMemberPassesPerMonth(model.id)
                .onSuccess { list ->
                    _monthStatisticsState.update { MonthStatisticsState.Success(list.size) }
                }
                .onFailure {
                    _monthStatisticsState.update { MonthStatisticsState.Error }
                }
        }
    }

    fun readSemesterStatistics() {
        val model = state.value.student ?: return
        if (semesterStatisticsState.value == SemesterStatisticsState.Progress) return

        _semesterStatisticsState.update { SemesterStatisticsState.Progress }
        viewModelScope.launch {
            readGroupMemberPassesPerSemesterUseCase.readGroupMemberPassesPerSemester(model.id)
                .onSuccess { list ->
                    _semesterStatisticsState.update { SemesterStatisticsState.Success(list.size) }
                }
                .onFailure {
                    _semesterStatisticsState.update { SemesterStatisticsState.Error }
                }
        }
    }

    fun setGroupMember(groupMemberModel: ReadGroupMemberModel) {
        _state.update { it.copy(student = groupMemberModel) }
    }

    sealed interface MonthStatisticsState {
        data object Initial : MonthStatisticsState
        data object Progress : MonthStatisticsState
        data object Error : MonthStatisticsState
        data class Success(val count: Int) : MonthStatisticsState
    }

    sealed interface SemesterStatisticsState {
        data object Initial : SemesterStatisticsState
        data object Progress : SemesterStatisticsState
        data object Error : SemesterStatisticsState
        data class Success(val count: Int) : SemesterStatisticsState
    }

    data class State(
        val student: ReadGroupMemberModel? = null
    )
}

























