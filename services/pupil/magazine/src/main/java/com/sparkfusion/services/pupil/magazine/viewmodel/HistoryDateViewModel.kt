package com.sparkfusion.services.pupil.magazine.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.GroupMemberModel
import com.sparkfusion.portdomainservices.pupil.portmagazine.usecase.IReadGroupMembersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HistoryDateViewModel @Inject constructor(
    private val readGroupMembersUseCase: IReadGroupMembersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    private val _readingState = MutableStateFlow<ReadingState>(ReadingState.Initial)
    val readingState: StateFlow<ReadingState> = _readingState.asStateFlow()

    fun readGroupMembers() {
        if (readingState.value is ReadingState.Success) return
        if (readingState.value == ReadingState.Progress) return

        _readingState.update { ReadingState.Progress }
        viewModelScope.launch {
            readGroupMembersUseCase.readGroupMembers()
                .onSuccess { list ->
                    if (list.isEmpty()) {
                        _readingState.update { ReadingState.Empty }
                    } else {
                        _state.update { it.copy(groupMemberId = list[0].id) }
                        _readingState.update { ReadingState.Success(list) }
                    }
                }
                .onFailure {
                    _readingState.update { ReadingState.Error }
                }
        }
    }

    fun clearReadingState() {
        _readingState.update { ReadingState.Initial }
    }

    fun updateGroupMemberId(id: Int) {
        _state.update { it.copy(groupMemberId = id) }
    }

    data class State(
        val groupMemberId: Int = -1
    )

    sealed interface ReadingState {
        data object Initial : ReadingState
        data object Error : ReadingState
        data object Progress : ReadingState
        data object Empty : ReadingState
        data class Success(val data: List<GroupMemberModel>) : ReadingState
    }
}
























