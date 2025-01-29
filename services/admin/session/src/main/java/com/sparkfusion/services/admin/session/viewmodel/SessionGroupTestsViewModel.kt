package com.sparkfusion.services.admin.session.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.portdomainservices.admin.portexam.model.GroupModel
import com.sparkfusion.portdomainservices.admin.portexam.model.ReadExamModel
import com.sparkfusion.portdomainservices.admin.portexam.usecase.IDeleteExamsUseCase
import com.sparkfusion.portdomainservices.admin.portexam.usecase.IReadExamsUseCase
import com.sparkfusion.portdomainservices.admin.portexam.usecase.IReadGroupByNamePartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionGroupTestsViewModel @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val readGroupByNamePartUseCase: IReadGroupByNamePartUseCase,
    private val readExamsUseCase: IReadExamsUseCase,
    private val deleteExamsUseCase: IDeleteExamsUseCase
) : ViewModel() {

    private val _groupReadingState = MutableStateFlow<List<GroupModel>>(emptyList())
    val groupReadingState: StateFlow<List<GroupModel>> = _groupReadingState.asStateFlow()

    private val _examReadingState = MutableStateFlow<ExamReadingState>(ExamReadingState.Initial)
    val examReadingState: StateFlow<ExamReadingState> = _examReadingState.asStateFlow()

    private val _deletingState = MutableStateFlow<DeletingState>(DeletingState.Initial)
    val deletingState: StateFlow<DeletingState> = _deletingState.asStateFlow()

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    fun readGroupsByNamePart() {
        val part = state.value.groupName.trim()
        if (part.isEmpty()) return

        viewModelScope.launch {
            readGroupByNamePartUseCase.readGroupByNamePart(part)
                .onSuccess { list ->
                    Log.d("TAGTAG", list.joinToString())
                    _groupReadingState.update { list }
                }
        }
    }

    private fun readExams() {
        if (state.value.groupId == -1) return

        _examReadingState.update { ExamReadingState.Progress }
        viewModelScope.launch {
            readExamsUseCase.readExams(state.value.groupId)
                .onSuccess {
                    val exams = it.filter { model -> model.type == 1 }
                    val tests = it.filter { model -> model.type == 2 }
                    _examReadingState.update { ExamReadingState.Success(exams, tests) }
                }
                .onFailure {
                    _examReadingState.update { ExamReadingState.Error }
                }
        }
    }

    fun deleteExams() {
        if (examReadingState.value !is ExamReadingState.Success) {
            _state.update { it.copy(selectedExams = emptyList()) }
            return
        }
        if (deletingState.value == DeletingState.Progress) {
            _state.update { it.copy(selectedExams = emptyList()) }
            return
        }

        _deletingState.update { DeletingState.Progress }
        viewModelScope.launch(ioDispatcher) {
            val exams = (examReadingState.value as ExamReadingState.Success).exams
            val tests = (examReadingState.value as ExamReadingState.Success).tests

            val selectedExams = state.value.selectedExams
            val newExams = exams.filter { it.id !in selectedExams }
            val newTests = tests.filter { it.id !in selectedExams }
            deleteExamsUseCase.deleteExams(state.value.selectedExams)
                .onSuccess {
                    _examReadingState.update { ExamReadingState.Success(newExams, newTests) }
                    _state.update { it.copy(selectedExams = emptyList()) }
                }
                .onFailure {
                    Log.d("TAGTAG", it.message.toString())
                    _deletingState.update { DeletingState.Error }
                    _state.update { it.copy(selectedExams = emptyList()) }
                }
        }
    }

    fun updateGroupId(id: Int) {
        _state.update { it.copy(groupId = id) }
        readExams()
    }

    fun updateGroupName(value: String) {
        _state.update { it.copy(groupName = value) }
    }

    fun addSelectedExam(id: Int) {
        val items = state.value.selectedExams.toMutableSet()
        items.add(id)

        _state.update { it.copy(selectedExams = items.toList()) }
    }

    fun deleteSelectedExam(id: Int) {
        val items = state.value.selectedExams.toMutableSet()
        items.removeIf { it == id }

        _state.update { it.copy(selectedExams = items.toList()) }
    }

    fun clearSelectedItems() {
        _state.update { it.copy(selectedExams = emptyList()) }
    }

    fun clearExamReadingState() {
        _examReadingState.update { ExamReadingState.Initial }
    }

    fun clearDeletingState() {
        _deletingState.update { DeletingState.Initial }
    }

    data class State(
        val groupName: String = "",
        val groupId: Int = -1,
        val selectedExams: List<Int> = emptyList()
    )

    sealed interface ExamReadingState {
        data object Initial : ExamReadingState
        data object Error : ExamReadingState
        data object Progress : ExamReadingState
        data class Success(val exams: List<ReadExamModel>, val tests: List<ReadExamModel>) :
            ExamReadingState
    }

    sealed interface DeletingState {
        data object Initial : DeletingState
        data object Error : DeletingState
        data object Progress : DeletingState
    }
}

























