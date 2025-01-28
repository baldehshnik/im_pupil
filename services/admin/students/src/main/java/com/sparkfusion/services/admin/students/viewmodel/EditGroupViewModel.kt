package com.sparkfusion.services.admin.students.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.admin.portstudents.model.CreateGroupMemberModel
import com.sparkfusion.portdomainservices.admin.portstudents.model.UpdateGroupMemberModel
import com.sparkfusion.portdomainservices.admin.portstudents.model.UpdateGroupModel
import com.sparkfusion.portdomainservices.admin.portstudents.usecase.IDeleteGroupUseCase
import com.sparkfusion.portdomainservices.admin.portstudents.usecase.IReadGroupWithMembersUseCase
import com.sparkfusion.portdomainservices.admin.portstudents.usecase.IUpdateGroupUseCase
import com.sparkfusion.services.admin.students.mapper.ReadGroupMemberInfoModelMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditGroupViewModel @Inject constructor(
    private val deleteGroupUseCase: IDeleteGroupUseCase,
    private val updateGroupUseCase: IUpdateGroupUseCase,
    private val readGroupWithMembersUseCase: IReadGroupWithMembersUseCase,
    private val readGroupMemberInfoModelMapper: ReadGroupMemberInfoModelMapper
) : ViewModel() {

    private val _deletingState = MutableStateFlow<DeletingState>(DeletingState.Initial)
    val deletingState: StateFlow<DeletingState> = _deletingState.asStateFlow()

    private val _readingState = MutableStateFlow<ReadingState>(ReadingState.Initial)
    val readingState: StateFlow<ReadingState> = _readingState.asStateFlow()

    private val _updatingState = MutableStateFlow<UpdatingState>(UpdatingState.Initial)
    val updatingState: StateFlow<UpdatingState> = _updatingState.asStateFlow()

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    fun readGroup(id: Int) {
        if (readingState.value == ReadingState.Progress) return

        _readingState.update { ReadingState.Progress }
        viewModelScope.launch {
            readGroupWithMembersUseCase.readGroupWithMembers(id)
                .onSuccess { model ->
                    _state.update {
                        it.copy(
                            id = model.id,
                            course = model.course,
                            groupMemberDtos = model.members.map { member ->
                                readGroupMemberInfoModelMapper.map(member)
                            }
                        )
                    }
                    _readingState.update { ReadingState.Success }
                }
                .onFailure {
                    _readingState.update { ReadingState.Error }
                }
        }
    }

    fun update() {
        if (readingState.value != ReadingState.Success) return
        if (updatingState.value == UpdatingState.Progress) return

        _updatingState.update { UpdatingState.Progress }
        viewModelScope.launch {
            val updateGroupModel =
                UpdateGroupModel(state.value.id, state.value.course, state.value.groupMemberDtos)
            updateGroupUseCase.updateGroup(updateGroupModel)
                .onSuccess {
                    _updatingState.update { UpdatingState.Success }
                }
                .onFailure {
                    _updatingState.update { UpdatingState.Error }
                }
        }
    }

    fun delete(id: Int) {
        if (deletingState.value == DeletingState.Progress) return

        _deletingState.update { DeletingState.Progress }
        viewModelScope.launch {
            deleteGroupUseCase.deleteGroupById(id)
                .onSuccess {
                    _deletingState.update { DeletingState.Success }
                }
                .onFailure {
                    _deletingState.update { DeletingState.Error }
                }
        }
    }

    fun updateCourse(course: Int) {
        _state.update { it.copy(course = course) }
    }

    fun removeStudent(student: UpdateGroupMemberModel) {
        val students = state.value.groupMemberDtos.toMutableList()
        students.remove(student)
        _state.update { it.copy(groupMemberDtos = students) }
    }

//    fun updateStudent(student: UpdateGroupMemberModel) {
//        val students = state.value.groupMemberDtos.toMutableList()
//        val currentStudent = students[currentStudentIndex].copy(
//            id = student.id,
//            firstname = student.firstname,
//            lastname = student.lastname,
//            patronymic = student.patronymic,
//            code = student.code
//        )
//
//        students[currentStudentIndex] = currentStudent
//        _state.update { it.copy(groupMemberDtos = students.toList()) }
//
//        currentStudentIndex = -1
//    }

    fun addStudent(student: CreateGroupMemberModel) {
        val students = state.value.groupMemberDtos.toMutableList()
        students.add(
            UpdateGroupMemberModel(
                null,
                student.firstname,
                student.lastname,
                student.patronymic,
                student.code
            )
        )
        _state.update { it.copy(groupMemberDtos = students) }
    }

    fun clearReadingState() {
        _readingState.update { ReadingState.Initial }
    }

    fun clearDeletingState() {
        _deletingState.update { DeletingState.Initial }
    }

    fun clearUpdatingState() {
        _updatingState.update { UpdatingState.Initial }
    }

    data class State(
        val id: Int = -1,
        val course: Int = 1,
        val groupMemberDtos: List<UpdateGroupMemberModel> = emptyList()
    )

    sealed interface UpdatingState {
        data object Initial : UpdatingState
        data object Error : UpdatingState
        data object Progress : UpdatingState
        data object Success : UpdatingState
    }

    sealed interface ReadingState {
        data object Initial : ReadingState
        data object Error : ReadingState
        data object Progress : ReadingState
        data object Success : ReadingState
    }

    sealed interface DeletingState {
        data object Initial : DeletingState
        data object Error : DeletingState
        data object Progress : DeletingState
        data object Success : DeletingState
    }
}




























