package com.sparkfusion.services.admin.students.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.admin.portstudents.model.GroupModel
import com.sparkfusion.portdomainservices.admin.portstudents.model.SpecialityModel
import com.sparkfusion.portdomainservices.admin.portstudents.usecase.IReadGroupsUseCase
import com.sparkfusion.portdomainservices.admin.portstudents.usecase.IReadSpecialityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(
    private val readSpecialityUseCase: IReadSpecialityUseCase,
    private val readGroupsUseCase: IReadGroupsUseCase
) : ViewModel() {

    private val _specialityState = MutableStateFlow<SpecialityState>(SpecialityState.Initial)
    val specialityState: StateFlow<SpecialityState> = _specialityState.asStateFlow()

    private val _groupState = MutableStateFlow<GroupState>(GroupState.Initial)
    val groupState: StateFlow<GroupState> = _groupState.asStateFlow()

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

    fun readGroups(specialityId: Int) {
        if (groupState.value == GroupState.Progress) return

        _groupState.update { GroupState.Progress }
        viewModelScope.launch {
            readGroupsUseCase.readGroupBySpeciality(specialityId)
                .onSuccess { list ->
                    _groupState.update { GroupState.Success(list) }
                }
                .onFailure {
                    _groupState.update { GroupState.Error }
                }
        }
    }

    fun clearSpecialityState() {
        _specialityState.update { SpecialityState.Initial }
    }

    fun clearGroupState() {
        _groupState.update { GroupState.Initial }
    }

    sealed interface GroupState {
        data object Initial : GroupState
        data object Error : GroupState
        data object Progress : GroupState
        data class Success(val data: List<GroupModel>) : GroupState
    }

    sealed interface SpecialityState {
        data object Initial : SpecialityState
        data object Error : SpecialityState
        data object Progress : SpecialityState
        data object Empty : SpecialityState
        data class Success(val data: List<SpecialityModel>) : SpecialityState
    }
}
























