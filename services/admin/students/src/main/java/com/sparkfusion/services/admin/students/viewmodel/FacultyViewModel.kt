package com.sparkfusion.services.admin.students.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.admin.portstudents.model.FacultyModel
import com.sparkfusion.portdomainservices.admin.portstudents.usecase.IReadFacultyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FacultyViewModel @Inject constructor(
    private val readFacultyUseCase: IReadFacultyUseCase
): ViewModel() {

    private val _facultyState = MutableStateFlow<FacultyState>(FacultyState.Initial)
    val facultyState: StateFlow<FacultyState> = _facultyState.asStateFlow()

    fun readFaculties() {
        if (facultyState.value == FacultyState.Progress) return

        _facultyState.update { FacultyState.Progress }
        viewModelScope.launch {
            readFacultyUseCase.readFaculties()
                .onSuccess { list ->
                    _facultyState.update { FacultyState.Success(list) }
                }
                .onFailure {
                    _facultyState.update { FacultyState.Error }
                }
        }
    }

    fun clearFacultyState() {
        _facultyState.update { FacultyState.Initial }
    }

    sealed interface FacultyState {
        data object Initial : FacultyState
        data object Error : FacultyState
        data object Progress : FacultyState
        data class Success(val data: List<FacultyModel>) : FacultyState
    }
}




























