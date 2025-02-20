package com.sparkfusion.services.pupil.statistics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.pupil.portstatistics.model.GroupMemberModel
import com.sparkfusion.portdomainservices.pupil.portstatistics.usecase.IReadGroupMembersForStatisticsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class GroupMembersViewModel @Inject constructor(
    private val readGroupMembersForStatisticsUseCase: IReadGroupMembersForStatisticsUseCase
) : ViewModel() {

    private val _readingState = MutableStateFlow<ReadingState>(ReadingState.Initial)
    internal val readingState: StateFlow<ReadingState> = _readingState.asStateFlow()

    internal fun read() {
        if (readingState.value is ReadingState.Success) return
        if (readingState.value == ReadingState.Progress) return

        _readingState.update { ReadingState.Progress }
        viewModelScope.launch {
            readGroupMembersForStatisticsUseCase.readGroupMembersForStatistics()
                .onSuccess { list ->
                    _readingState.update { ReadingState.Success(list) }
                }
                .onFailure {
                    _readingState.update { ReadingState.Error }
                }
        }
    }

    internal fun clearReadingState() {
        _readingState.update { ReadingState.Initial }
    }

    internal sealed interface ReadingState {
        data object Initial : ReadingState
        data object Progress : ReadingState
        data object Error : ReadingState
        data class Success(val data: List<GroupMemberModel>) : ReadingState
    }
}


























