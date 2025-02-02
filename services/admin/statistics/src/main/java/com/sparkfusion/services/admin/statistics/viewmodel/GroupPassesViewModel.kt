package com.sparkfusion.services.admin.statistics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.admin.portstatistics.model.ReadFullPassWithGroupMemberModel
import com.sparkfusion.portdomainservices.admin.portstatistics.usecase.IReadPassesOfGroupPerMonthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class GroupPassesViewModel @Inject constructor(
    private val readPassesOfGroupPerMonthUseCase: IReadPassesOfGroupPerMonthUseCase
) : ViewModel() {

    private val _readingState = MutableStateFlow<ReadingState>(ReadingState.Initial)
    val readingState: StateFlow<ReadingState> = _readingState.asStateFlow()

    fun read(groupId: Int) {
        if (readingState.value == ReadingState.Progress) return

        _readingState.update { ReadingState.Progress }
        viewModelScope.launch {
            readPassesOfGroupPerMonthUseCase.readPassesOfGroupPerMonth(groupId)
                .onSuccess { list ->
                    val data = list.groupBy { it.date }
                    _readingState.update { ReadingState.Success(data) }
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
        data object Error : ReadingState
        data object Progress : ReadingState
        data class Success(val data: Map<Instant, List<ReadFullPassWithGroupMemberModel>>) :
            ReadingState
    }
}




















