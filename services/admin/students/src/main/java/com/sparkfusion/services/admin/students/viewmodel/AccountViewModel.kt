package com.sparkfusion.services.admin.students.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.admin.portstudents.model.ReadGroupMemberModel
import com.sparkfusion.portdomainservices.admin.portstudents.usecase.IMakeStudentAPrefectUseCase
import com.sparkfusion.portdomainservices.admin.portstudents.usecase.IReadGroupMemberByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val readGroupMemberByIdUseCase: IReadGroupMemberByIdUseCase,
    private val makeStudentAPrefectUseCase: IMakeStudentAPrefectUseCase
) : ViewModel() {

    private val _readingState = MutableStateFlow<ReadingState>(ReadingState.Initial)
    val readingState: StateFlow<ReadingState> = _readingState.asStateFlow()

    private val _makeAPrefectState = MutableStateFlow<MakeAPrefectState>(MakeAPrefectState.Initial)
    val makeAPrefectState: StateFlow<MakeAPrefectState> = _makeAPrefectState.asStateFlow()

    fun readGroupMember(id: Int) {
        if (readingState.value == ReadingState.Progress) return

        _readingState.update { ReadingState.Progress }
        viewModelScope.launch {
            readGroupMemberByIdUseCase.readGroupMemberById(id)
                .onSuccess { model ->
                    _readingState.update { ReadingState.Success(model) }
                }
                .onFailure {
                    _readingState.update { ReadingState.Error }
                }
        }
    }

    fun makeAPrefect(id: Int) {
        if (makeAPrefectState.value == MakeAPrefectState.Progress) return

        _makeAPrefectState.update { MakeAPrefectState.Progress }
        viewModelScope.launch {
            makeStudentAPrefectUseCase.makeStudentAPrefect(id)
                .onSuccess {
                    if (readingState.value is ReadingState.Success) {
                        val model = (readingState.value as ReadingState.Success).model
                        val newModel = model.copy(isPrefect = true)

                        _readingState.update { ReadingState.Success(newModel) }
                    }
                }
                .onFailure {
                    _makeAPrefectState.update { MakeAPrefectState.Error }
                }
        }
    }

    fun clearMakeAPrefectState() {
        _makeAPrefectState.update { MakeAPrefectState.Initial }
    }

    fun clearReadingState() {
        _readingState.update { ReadingState.Initial }
    }

    sealed interface MakeAPrefectState {
        data object Initial : MakeAPrefectState
        data object Error : MakeAPrefectState
        data object Progress : MakeAPrefectState
    }

    sealed interface ReadingState {
        data object Initial : ReadingState
        data object Error : ReadingState
        data object Progress : ReadingState
        data class Success(val model: ReadGroupMemberModel) : ReadingState
    }
}























