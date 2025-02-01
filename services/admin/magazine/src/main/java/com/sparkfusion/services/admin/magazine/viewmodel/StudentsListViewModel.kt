package com.sparkfusion.services.admin.magazine.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.admin.portmagazine.model.ReadGroupMemberWithPassesModel
import com.sparkfusion.portdomainservices.admin.portmagazine.model.UpdateInfoModel
import com.sparkfusion.portdomainservices.admin.portmagazine.model.UpdatePassesStatusModel
import com.sparkfusion.portdomainservices.admin.portmagazine.usecase.IReadPassesUseCase
import com.sparkfusion.portdomainservices.admin.portmagazine.usecase.IUpdatePassesOfGroupMemberUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class StudentsListViewModel @Inject constructor(
    private val readPassesUseCase: IReadPassesUseCase,
    private val updatePassesOfGroupMemberUseCase: IUpdatePassesOfGroupMemberUseCase
) : ViewModel() {

    private val _readingState = MutableStateFlow<ReadingState>(ReadingState.Initial)
    val readingState: StateFlow<ReadingState> = _readingState.asStateFlow()

    private val _updatingState = MutableStateFlow<UpdatingState>(UpdatingState.Initial)
    val updatingState: StateFlow<UpdatingState> = _updatingState.asStateFlow()

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    fun read(groupId: Int, lessonId: Int) {
        if (readingState.value == ReadingState.Progress) return

        _readingState.update { ReadingState.Progress }
        viewModelScope.launch {
            readPassesUseCase.readPasses(groupId, lessonId)
                .onSuccess { list ->
                    _readingState.update { ReadingState.Success(list) }
                }
                .onFailure {
                    _readingState.update { ReadingState.Error }
                }
        }
    }

    fun update(lessonId: Int, newStatus: Int) {
        if (readingState.value !is ReadingState.Success) return

        val data = (readingState.value as ReadingState.Success).data
        val selectedGroupMembers = state.value.selectedGroupMembers
        viewModelScope.launch {
            val updatingItems = data.asSequence()
                .filter { it.getGroupMemberDto.id in selectedGroupMembers }
                .map { UpdateInfoModel(it.getPassDto.id, it.getGroupMemberDto.id, newStatus) }
                .toList()

            updatePassesOfGroupMemberUseCase.updatePassesOfGroupMember(
                UpdatePassesStatusModel(
                    lessonId = lessonId,
                    date = LocalDate.now(),
                    updateInfos = updatingItems
                )
            ).onSuccess {
                val newData = data.map {
                    if (it.getGroupMemberDto.id in selectedGroupMembers) {
                        it.copy(getPassDto = it.getPassDto.copy(status = newStatus))
                    } else it
                }

                _readingState.update { ReadingState.Initial }
                _readingState.update { ReadingState.Success(newData) }
            }.onFailure {
                _updatingState.update { UpdatingState.Error }
            }
        }
    }

    fun clearUpdatingState() {
        _updatingState.update { UpdatingState.Initial }
    }

    fun clearReadingState() {
        _readingState.update { ReadingState.Initial }
    }

    fun clearSelectionState() {
        _state.update { State() }
    }

    fun addSelectedGroupMember(id: Int) {
        val items = state.value.selectedGroupMembers.toMutableSet()
        items.add(id)

        _state.update { it.copy(selectedGroupMembers = items.toList()) }
    }

    fun deleteSelectedGroupMember(id: Int) {
        val items = state.value.selectedGroupMembers.toMutableSet()
        items.removeIf { it == id }

        _state.update { it.copy(selectedGroupMembers = items.toList()) }
    }

    data class State(
        val selectedGroupMembers: List<Int> = emptyList()
    )

    sealed interface UpdatingState {
        data object Initial : UpdatingState
        data object Error : UpdatingState
    }

    sealed interface ReadingState {
        data object Initial : ReadingState
        data object Error : ReadingState
        data object Progress : ReadingState
        data class Success(val data: List<ReadGroupMemberWithPassesModel>) : ReadingState
    }
}























