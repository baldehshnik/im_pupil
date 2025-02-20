package com.sparkfusion.services.pupil.session.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.pupil.portsession.IReadExamsUseCase
import com.sparkfusion.portdomainservices.pupil.portsession.SessionModel
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SessionViewModel @Inject constructor(
    private val readSessionUseCase: Lazy<IReadExamsUseCase>
) : ViewModel() {

    private val _examReadingState = MutableStateFlow<ExamReadingState>(ExamReadingState.Initial)
    val examReadingState: StateFlow<ExamReadingState> = _examReadingState.asStateFlow()

    fun readExams() {
        if (examReadingState.value is ExamReadingState.Success) return
        if (examReadingState.value == ExamReadingState.Progress) return

        _examReadingState.update { ExamReadingState.Progress }
        viewModelScope.launch {
            readSessionUseCase.get().readExams()
                .onSuccess {
                    val exams = it.filter { model -> model.type == 1 }
                    val tests = it.filter { model -> model.type == 2 }
                    _examReadingState.update { ExamReadingState.Success(exams, tests) }
                }
                .onFailure {
                    Log.d("TAGTAG", it.message.toString())
                    _examReadingState.update { ExamReadingState.Error }
                }
        }
    }

    fun clearExamReadingState() {
        _examReadingState.update { ExamReadingState.Initial }
    }

    sealed interface ExamReadingState {
        data object Initial : ExamReadingState
        data object Error : ExamReadingState
        data object Progress : ExamReadingState
        data class Success(val exams: List<SessionModel>, val tests: List<SessionModel>) :
            ExamReadingState
    }
}

























