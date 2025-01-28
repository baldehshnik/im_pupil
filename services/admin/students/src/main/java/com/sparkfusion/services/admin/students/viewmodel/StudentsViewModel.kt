package com.sparkfusion.services.admin.students.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.admin.portstudents.model.ReadGroupMemberModel
import com.sparkfusion.portdomainservices.admin.portstudents.usecase.IReadGroupMembersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentsViewModel @Inject constructor(
    private val readGroupMembersUseCase: IReadGroupMembersUseCase
): ViewModel() {

    private val _readingState = MutableStateFlow<ReadingState>(ReadingState.Initial)
    val readingState: StateFlow<ReadingState> = _readingState.asStateFlow()

    fun readGroupMembers(groupId: Int) {
        if (readingState.value == ReadingState.Progress) return

        _readingState.update { ReadingState.Progress }
        viewModelScope.launch {
            readGroupMembersUseCase.readGroupMembers(groupId)
                .onSuccess { list ->
                    _readingState.update { ReadingState.Success(list) }
                }
                .onFailure {
                    Log.d("TAGTAG", it.message.toString())
                    _readingState.update { ReadingState.Error }
                }
        }
    }

    fun clearReadingState() {
        _readingState.update { ReadingState.Initial }
    }

    sealed interface ReadingState {
        data object Initial : ReadingState
        data object Error : ReadingState
        data object Progress : ReadingState
        data class Success(val data: List<ReadGroupMemberModel>) : ReadingState
    }
}



























