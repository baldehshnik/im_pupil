package com.sparkfusion.services.admin.session.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.admin.portexam.model.AddExamModel
import com.sparkfusion.portdomainservices.admin.portexam.usecase.ICreateExamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class SessionAddingViewModel @Inject constructor(
    private val createExamUseCase: ICreateExamUseCase
) : ViewModel() {

    private val _checkState = MutableStateFlow<CheckState>(CheckState.Initial)
    val checkState: StateFlow<CheckState> = _checkState.asStateFlow()

    private val _creatingState = MutableStateFlow<CreatingState>(CreatingState.Initial)
    val creatingState: StateFlow<CreatingState> = _creatingState.asStateFlow()

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    fun create(groupId: Int, type: Int) {
        if (creatingState.value == CreatingState.Progress) return

        if (state.value.name.isEmpty()) {
            _checkState.update { CheckState.NameIsEmpty }
            return
        }

        if (state.value.audience.isEmpty()) {
            _checkState.update { CheckState.AudienceIsEmpty }
            return
        }

        _creatingState.update { CreatingState.Progress }
        viewModelScope.launch {
            val model = AddExamModel(
                groupId = groupId,
                type = type,
                name = state.value.name,
                audience = state.value.audience,
                dateTime = state.value.dateAndTimeCalendar.toInstant()
            )
            createExamUseCase.createExam(model)
                .onSuccess {
                    _creatingState.update { CreatingState.Success }
                }
                .onFailure {
                    _creatingState.update { CreatingState.Error }
                }
        }
    }

    fun updateName(value: String) {
        _state.update { it.copy(name = value) }
    }

    fun updateAudience(value: String) {
        _state.update { it.copy(audience = value) }
    }

    fun clearCreatingState() {
        _creatingState.update { CreatingState.Initial }
    }

    fun clearCheckState() {
        _checkState.update { CheckState.Initial }
    }

    data class State(
        val dateAndTimeCalendar: Calendar = Calendar.getInstance(),
        val name: String = "",
        val audience: String = ""
    )

    sealed interface CheckState {
        data object Initial : CheckState
        data object NameIsEmpty : CheckState
        data object AudienceIsEmpty : CheckState
    }

    sealed interface CreatingState {
        data object Initial : CreatingState
        data object Error : CreatingState
        data object Progress : CreatingState
        data object Success : CreatingState
    }
}

























