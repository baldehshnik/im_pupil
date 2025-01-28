package com.sparkfusion.services.admin.students.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.admin.portstudents.model.CreateGroupMemberModel
import com.sparkfusion.portdomainservices.admin.portstudents.model.CreateGroupModel
import com.sparkfusion.portdomainservices.admin.portstudents.model.SpecialityModel
import com.sparkfusion.portdomainservices.admin.portstudents.usecase.ICreateGroupUseCase
import com.sparkfusion.portdomainservices.admin.portstudents.usecase.IReadSpecialityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddGroupViewModel @Inject constructor(
    private val createGroupUseCase: ICreateGroupUseCase,
    private val readSpecialityUseCase: IReadSpecialityUseCase
) : ViewModel() {

    private val _creatingState = MutableStateFlow<CreatingState>(CreatingState.Initial)
    val creatingState: StateFlow<CreatingState> = _creatingState.asStateFlow()

    private val _checkState = MutableStateFlow<CheckState>(CheckState.Initial)
    val checkState: StateFlow<CheckState> = _checkState.asStateFlow()

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    private val _specialityState = MutableStateFlow<SpecialityState>(
        SpecialityState.Initial
    )
    val specialityState: StateFlow<SpecialityState> = _specialityState.asStateFlow()

    fun readSpecialities(facultyId: Int) {
        if (_specialityState.value == SpecialityState.Progress) return

        _specialityState.update { SpecialityState.Progress }
        viewModelScope.launch {
            readSpecialityUseCase.readSpecialitiesByFaculty(facultyId)
                .onSuccess { list ->
                    if (list.isEmpty()) {
                        _specialityState.update { SpecialityState.Empty }
                    } else {
                        _specialityState.update { SpecialityState.Success(list) }
                    }
                }
                .onFailure {
                    _specialityState.update { SpecialityState.Error }
                }
        }
    }

    fun create() {
        if (creatingState.value == CreatingState.Progress) return

        if (state.value.specialityId == -1) {
            _checkState.update { CheckState.SpecialityNotSelected }
            return
        }

        if (state.value.course == -1) {
            _checkState.update { CheckState.CourseNotSelected }
            return
        }

        if (state.value.name.length < 2) {
            _checkState.update { CheckState.NameIsTooShort }
            return
        }

        _creatingState.update { CreatingState.Progress }
        viewModelScope.launch {
            val model = CreateGroupModel(
                state.value.specialityId,
                state.value.name,
                state.value.course,
                state.value.students
            )
            createGroupUseCase.createGroup(model)
                .onSuccess {
                    _creatingState.update { CreatingState.Success }
                }
                .onFailure {
                    _creatingState.update { CreatingState.Error }
                }
        }
    }

    fun removeStudent(student: CreateGroupMemberModel) {
        val students = state.value.students.toMutableList()
        students.remove(student)
        _state.update { it.copy(students = students) }
    }

    fun addStudent(student: CreateGroupMemberModel) {
        val students = state.value.students.toMutableList()
        students.add(student)
        _state.update { it.copy(students = students) }
    }

    fun updateName(newValue: String) {
        _state.update { it.copy(name = newValue) }
    }

    fun updateCurse(course: Int) {
        _state.update { it.copy(course = course) }
    }

    fun updateSpecialityId(id: Int) {
        _state.update { it.copy(specialityId = id) }
    }

    fun clearCheckState() {
        _checkState.update { CheckState.Initial }
    }

    fun clearCreatingState() {
        _creatingState.update { CreatingState.Initial }
    }

    fun clearSpecialityState() {
        _specialityState.update { SpecialityState.Initial }
    }

    data class State(
        val specialityId: Int = -1,
        val course: Int = 1,
        val name: String = "",
        val students: List<CreateGroupMemberModel> = emptyList()
    )

    sealed interface SpecialityState {
        data object Initial : SpecialityState
        data object Error : SpecialityState
        data object Progress : SpecialityState
        data object Empty : SpecialityState
        data class Success(val data: List<SpecialityModel>) : SpecialityState
    }

    sealed interface CheckState {
        data object Initial : CheckState
        data object SpecialityNotSelected : CheckState
        data object CourseNotSelected : CheckState
        data object NameIsTooShort : CheckState
    }

    sealed interface CreatingState {
        data object Initial : CreatingState
        data object Error : CreatingState
        data object Progress : CreatingState
        data object Success : CreatingState
    }
}





























