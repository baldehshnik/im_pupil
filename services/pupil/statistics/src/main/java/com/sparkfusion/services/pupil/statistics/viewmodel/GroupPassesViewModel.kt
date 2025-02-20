package com.sparkfusion.services.pupil.statistics.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.pupil.portstatistics.model.PassWithGroupMemberModel
import com.sparkfusion.portdomainservices.pupil.portstatistics.usecase.IReadPassesOfGroupPerMonthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
internal class GroupPassesViewModel @Inject constructor(
    private val readPassesOfGroupPerMonthUseCase: IReadPassesOfGroupPerMonthUseCase
): ViewModel() {

    private val _readingState = MutableStateFlow<ReadingState>(ReadingState.Initial)
    internal val readingState: StateFlow<ReadingState> = _readingState.asStateFlow()

    internal fun read() {
        if (readingState.value is ReadingState.Success) return
        if (readingState.value == ReadingState.Progress) return

        _readingState.update { ReadingState.Progress }
        viewModelScope.launch {
            readPassesOfGroupPerMonthUseCase.readPassesOfGroupPerMonth()
                .onSuccess { list ->
                    val data = list.groupBy { it.date }
                    _readingState.update { ReadingState.Success(data) }
                }
                .onFailure {
                    Log.d("TAGTAG", it.message.toString())
                    _readingState.update { ReadingState.Error }
                }
        }
    }

    internal fun clearReadingState() {
        _readingState.update { ReadingState.Initial }
    }

    internal sealed interface ReadingState {
        data object Initial : ReadingState
        data object Error : ReadingState
        data object Progress : ReadingState
        data class Success(val data: Map<Instant, List<PassWithGroupMemberModel>>) :
            ReadingState
    }
}
























