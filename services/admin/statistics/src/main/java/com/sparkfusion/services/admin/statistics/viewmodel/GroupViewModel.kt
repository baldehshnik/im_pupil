package com.sparkfusion.services.admin.statistics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.admin.portstatistics.model.ReadGroupMemberModel
import com.sparkfusion.portdomainservices.admin.portstatistics.usecase.IReadGroupMembersForStatisticsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupViewModel @Inject constructor(
    private val readGroupMembersUseCase: IReadGroupMembersForStatisticsUseCase
) : ViewModel() {

    private val _readingState = MutableStateFlow<ReadingState>(ReadingState.Initial)
    val readingState: StateFlow<ReadingState> = _readingState.asStateFlow()

    fun read(groupId: Int) {
        if (readingState.value == ReadingState.Progress) return

        _readingState.update { ReadingState.Progress }
        viewModelScope.launch {
            readGroupMembersUseCase.readGroupMembersForStatistics(groupId)
                .onSuccess { list ->
                    _readingState.update { ReadingState.Success(list) }
                }
                .onFailure {
                    _readingState.update { ReadingState.Error }
                }
        }
    }

    fun clearReadingState() {
        _readingState.update { ReadingState.Initial }
    }

    sealed interface ReadingState {
        data object Initial : ReadingState
        data object Progress : ReadingState
        data object Error : ReadingState
        data class Success(val data: List<ReadGroupMemberModel>) : ReadingState
    }
}
























