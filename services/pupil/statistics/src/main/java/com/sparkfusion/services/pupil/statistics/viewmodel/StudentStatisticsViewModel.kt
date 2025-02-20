package com.sparkfusion.services.pupil.statistics.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.pupil.portstatistics.model.GroupMemberModel
import com.sparkfusion.portdomainservices.pupil.portstatistics.usecase.IReadGroupMemberByIdUseCase
import com.sparkfusion.portdomainservices.pupil.portstatistics.usecase.IReadGroupMemberPassesPerMonthUseCase
import com.sparkfusion.portdomainservices.pupil.portstatistics.usecase.IReadGroupMemberPassesPerSemesterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class StudentStatisticsViewModel @Inject constructor(
    private val readGroupMemberByIdUseCase: IReadGroupMemberByIdUseCase,
    private val readGroupMemberPassesPerMonthUseCase: IReadGroupMemberPassesPerMonthUseCase,
    private val readGroupMemberPassesPerSemesterUseCase: IReadGroupMemberPassesPerSemesterUseCase
) : ViewModel() {

    private val _monthStatisticsState =
        MutableStateFlow<MonthStatisticsState>(MonthStatisticsState.Initial)
    internal val monthStatisticsState: StateFlow<MonthStatisticsState> =
        _monthStatisticsState.asStateFlow()

    private val _semesterStatisticsState =
        MutableStateFlow<SemesterStatisticsState>(SemesterStatisticsState.Initial)
    internal val semesterStatisticsState: StateFlow<SemesterStatisticsState> =
        _semesterStatisticsState.asStateFlow()

    private val _studentReadingState =
        MutableStateFlow<StudentReadingState>(StudentReadingState.Initial)
    internal val studentReadingState: StateFlow<StudentReadingState> =
        _studentReadingState.asStateFlow()

    internal fun readStudent(id: Int) {
        if (studentReadingState.value is StudentReadingState.Success) return
        if (studentReadingState.value == StudentReadingState.Progress) return

        _studentReadingState.update { StudentReadingState.Progress }
        viewModelScope.launch {
            readGroupMemberByIdUseCase.readGroupMemberById(id)
                .onSuccess { model ->
                    readMonthStatistics(id)
                    readSemesterStatistics(id)
                    _studentReadingState.update { StudentReadingState.Success(model) }
                }
                .onFailure {
                    Log.d("TAGTAG", it.message.toString())
                    _studentReadingState.update { StudentReadingState.Error }
                }
        }
    }

    private fun readMonthStatistics(id: Int) {
        if (monthStatisticsState.value is MonthStatisticsState.Success) return
        if (monthStatisticsState.value == MonthStatisticsState.Progress) return

        _monthStatisticsState.update { MonthStatisticsState.Progress }
        viewModelScope.launch {
            readGroupMemberPassesPerMonthUseCase.readGroupMemberPassesPerMonth(id)
                .onSuccess { list ->
                    _monthStatisticsState.update { MonthStatisticsState.Success(list.size) }
                }
                .onFailure {
                    Log.d("TAGTAG", it.message.toString())
                    _monthStatisticsState.update { MonthStatisticsState.Error }
                }
        }
    }

    private fun readSemesterStatistics(id: Int) {
        if (semesterStatisticsState.value is SemesterStatisticsState.Success) return
        if (semesterStatisticsState.value == SemesterStatisticsState.Progress) return

        _semesterStatisticsState.update { SemesterStatisticsState.Progress }
        viewModelScope.launch {
            readGroupMemberPassesPerSemesterUseCase.readGroupMemberPassesPerSemester(id)
                .onSuccess { list ->
                    _semesterStatisticsState.update { SemesterStatisticsState.Success(list.size) }
                }
                .onFailure {
                    Log.d("TAGTAG", it.message.toString())
                    _semesterStatisticsState.update { SemesterStatisticsState.Error }
                }
        }
    }
    internal fun clearStudentReading() {
        _studentReadingState.update { StudentReadingState.Initial }
    }

    internal sealed interface StudentReadingState {
        data object Initial : StudentReadingState
        data object Progress : StudentReadingState
        data object Error : StudentReadingState
        data class Success(val model: GroupMemberModel) : StudentReadingState
    }

    internal sealed interface MonthStatisticsState {
        data object Initial : MonthStatisticsState
        data object Progress : MonthStatisticsState
        data object Error : MonthStatisticsState
        data class Success(val count: Int) : MonthStatisticsState
    }

    internal sealed interface SemesterStatisticsState {
        data object Initial : SemesterStatisticsState
        data object Progress : SemesterStatisticsState
        data object Error : SemesterStatisticsState
        data class Success(val count: Int) : SemesterStatisticsState
    }
}




























