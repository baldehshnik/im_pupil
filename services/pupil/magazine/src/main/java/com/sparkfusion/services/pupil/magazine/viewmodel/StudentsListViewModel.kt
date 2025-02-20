package com.sparkfusion.services.pupil.magazine.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.AccountModel
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.GroupMemberWithPassModel
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.UpdateInfoModel
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.UpdatePassesStatusModel
import com.sparkfusion.portdomainservices.pupil.portmagazine.usecase.IReadPassesUseCase
import com.sparkfusion.portdomainservices.pupil.portmagazine.usecase.IReadPupilAccountUseCase
import com.sparkfusion.portdomainservices.pupil.portmagazine.usecase.IUpdatePassesUseCase
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
internal class StudentsListViewModel @Inject constructor(
    private val readPassesUseCase: Lazy<IReadPassesUseCase>,
    private val updatePassesUseCase: Lazy<IUpdatePassesUseCase>,
    private val readAccountUseCase: Lazy<IReadPupilAccountUseCase>
) : ViewModel() {

    private val _readingState = MutableStateFlow<ReadingState>(ReadingState.Initial)
    internal val readingState: StateFlow<ReadingState> = _readingState.asStateFlow()

    private val _updatingState = MutableStateFlow<UpdatingState>(UpdatingState.Initial)
    internal val updatingState: StateFlow<UpdatingState> = _updatingState.asStateFlow()

    private val _state = MutableStateFlow(State())
    internal val state: StateFlow<State> = _state.asStateFlow()

    private val _accountReadingState =
        MutableStateFlow<AccountReadingState>(AccountReadingState.Initial)
    internal val accountReadingState: StateFlow<AccountReadingState> =
        _accountReadingState.asStateFlow()

    internal fun readAccount() {
        if (accountReadingState.value is AccountReadingState.Success) return
        if (accountReadingState.value == AccountReadingState.Progress) return

        _accountReadingState.update { AccountReadingState.Progress }
        viewModelScope.launch {
            readAccountUseCase.get().readPupilAccount()
                .onSuccess { model ->
                    _accountReadingState.update { AccountReadingState.Success(model) }
                }
                .onFailure {
                    _accountReadingState.update { AccountReadingState.Error }
                }
        }
    }

    internal fun read(lessonId: Int) {
        if (readingState.value == ReadingState.Progress) return

        _readingState.update { ReadingState.Progress }
        viewModelScope.launch {
            readPassesUseCase.get().readPasses(lessonId)
                .onSuccess { list ->
                    _readingState.update { ReadingState.Success(list) }
                }
                .onFailure {
                    _readingState.update { ReadingState.Error }
                }
        }
    }

    internal fun update(lessonId: Int, newStatus: Int) {
        if (readingState.value !is ReadingState.Success) return

        val data = (readingState.value as ReadingState.Success).data
        val selectedGroupMembers = state.value.selectedGroupMembers
        viewModelScope.launch {
            val updatingItems = data.asSequence()
                .filter { it.getGroupMemberDto.id in selectedGroupMembers }
                .map { UpdateInfoModel(it.getPassDto.id, it.getGroupMemberDto.id, newStatus) }
                .toList()

            updatePassesUseCase.get().updatePasses(
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
                Log.d("TAGTAG", it.message.toString())
                _updatingState.update { UpdatingState.Error }
            }
        }
    }

    internal fun clearUpdatingState() {
        _updatingState.update { UpdatingState.Initial }
    }

    internal fun clearReadingState() {
        _readingState.update { ReadingState.Initial }
    }

    internal fun clearSelectionState() {
        _state.update { State() }
    }

    internal fun addSelectedGroupMember(id: Int) {
        val items = state.value.selectedGroupMembers.toMutableSet()
        items.add(id)

        _state.update { it.copy(selectedGroupMembers = items.toList()) }
    }

    internal fun deleteSelectedGroupMember(id: Int) {
        val items = state.value.selectedGroupMembers.toMutableSet()
        items.removeIf { it == id }

        _state.update { it.copy(selectedGroupMembers = items.toList()) }
    }

    internal fun clearAccountReadingState() {
        _accountReadingState.update { AccountReadingState.Initial }
    }

    internal data class State(
        val selectedGroupMembers: List<Int> = emptyList()
    )

    internal sealed interface AccountReadingState {
        data object Initial : AccountReadingState
        data object Error : AccountReadingState
        data object Progress : AccountReadingState
        data class Success(val model: AccountModel?) : AccountReadingState
    }

    internal sealed interface UpdatingState {
        data object Initial : UpdatingState
        data object Error : UpdatingState
    }

    internal sealed interface ReadingState {
        data object Initial : ReadingState
        data object Error : ReadingState
        data object Progress : ReadingState
        data class Success(val data: List<GroupMemberWithPassModel>) : ReadingState
    }
}





















