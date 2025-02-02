package com.sparkfusion.services.admin.statistics.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.admin.portstatistics.model.GroupModel
import com.sparkfusion.portdomainservices.admin.portstatistics.usecase.IReadGroupByNamePartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupSearchViewModel @Inject constructor(
    private val readGroupByNamePartUseCase: IReadGroupByNamePartUseCase
): ViewModel() {

    private val _groupReadingState = MutableStateFlow<List<GroupModel>>(emptyList())
    val groupReadingState: StateFlow<List<GroupModel>> = _groupReadingState.asStateFlow()

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    fun readGroupsByNamePart() {
        val part = state.value.groupName.trim()
        if (part.isEmpty()) return

        viewModelScope.launch {
            readGroupByNamePartUseCase.readGroupByNamePart(part)
                .onSuccess { list ->
                    Log.d("TAGTAG", list.joinToString())
                    _groupReadingState.update { emptyList() }
                    _groupReadingState.update { list }
                }
        }
    }

    fun updateGroupId(id: Int) {
        _state.update { it.copy(groupId = id) }
    }

    fun updateGroupName(value: String) {
        _state.update { it.copy(groupName = value) }
    }

    data class State(
        val groupName: String = "",
        val groupId: Int = -1
    )
}





















