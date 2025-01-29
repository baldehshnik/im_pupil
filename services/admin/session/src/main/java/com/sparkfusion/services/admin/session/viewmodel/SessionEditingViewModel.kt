package com.sparkfusion.services.admin.session.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.admin.portexam.model.ReadExamModel
import com.sparkfusion.portdomainservices.admin.portexam.model.UpdateExamModel
import com.sparkfusion.portdomainservices.admin.portexam.usecase.IReadExamByIdUseCase
import com.sparkfusion.portdomainservices.admin.portexam.usecase.IUpdateExamUseCase
import com.sparkfusion.services.admin.session.viewmodel.SessionAddingViewModel.CheckState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Calendar
import java.util.Date
import java.util.TimeZone
import javax.inject.Inject

@HiltViewModel
class SessionEditingViewModel @Inject constructor(
    private val readExamByIdUseCase: IReadExamByIdUseCase,
    private val updateExamUseCase: IUpdateExamUseCase
) : ViewModel() {

    private val _readingState = MutableStateFlow<ReadingState>(ReadingState.Initial)
    val readingState: StateFlow<ReadingState> = _readingState.asStateFlow()

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    private val _checkState = MutableStateFlow<CheckState>(CheckState.Initial)
    val checkState: StateFlow<CheckState> = _checkState.asStateFlow()

    private val _updatingState = MutableStateFlow<UpdatingState>(UpdatingState.Initial)
    val updatingState: StateFlow<UpdatingState> = _updatingState.asStateFlow()

    fun read(id: Int) {
        if (readingState.value == ReadingState.Progress) return

        _readingState.update { ReadingState.Progress }
        viewModelScope.launch {
            readExamByIdUseCase.readExamById(id)
                .onSuccess { model ->
                    _state.update {
                        it.copy(
                            name = model.name,
                            audience = model.audience,
                            dateAndTimeCalendar = instantToCalendarUTC(model.dateTime)
                        )
                    }
                    _readingState.update { ReadingState.Success(model) }
                }
                .onFailure {
                    _readingState.update { ReadingState.Error }
                }
        }
    }

    fun update() {
        if (updatingState.value == UpdatingState.Progress) return

        if (state.value.name.isEmpty()) {
            _checkState.update { CheckState.NameIsEmpty }
            return
        }

        if (state.value.audience.isEmpty()) {
            _checkState.update { CheckState.AudienceIsEmpty }
            return
        }

        _updatingState.update { UpdatingState.Progress }
        viewModelScope.launch {
            if (readingState.value !is ReadingState.Success) return@launch

            val state = (readingState.value as ReadingState.Success).model
            val model = UpdateExamModel(
                id = state.id,
                type = state.type,
                name = this@SessionEditingViewModel.state.value.name,
                audience = this@SessionEditingViewModel.state.value.audience,
                dateTime = this@SessionEditingViewModel.state.value.dateAndTimeCalendar.toInstant(),
                status = state.status
            )
            updateExamUseCase.updateExam(model)
                .onSuccess {
                    _updatingState.update { UpdatingState.Success }
                }
                .onFailure {
                    _updatingState.update { UpdatingState.Error }
                }
        }
    }

    fun updateName(value: String) {
        _state.update { it.copy(name = value) }
    }

    fun updateAudience(value: String) {
        _state.update { it.copy(audience = value) }
    }

    fun clearReadingState() {
        _readingState.update { ReadingState.Initial }
    }

    fun clearCheckState() {
        _checkState.update { CheckState.Initial }
    }

    fun clearUpdatingState() {
        _updatingState.update { UpdatingState.Initial }
    }

    private fun instantToCalendarUTC(instant: Instant): Calendar {
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        calendar.time = Date.from(instant)
        return calendar
    }

    sealed interface ReadingState {
        data object Initial : ReadingState
        data object Error : ReadingState
        data object Progress : ReadingState
        data class Success(val model: ReadExamModel) : ReadingState
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

    sealed interface UpdatingState {
        data object Initial : UpdatingState
        data object Error : UpdatingState
        data object Progress : UpdatingState
        data object Success : UpdatingState
    }
}
























