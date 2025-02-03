package com.sparkfusion.services.admin.sections.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.admin.portsections.model.ReadSectionModel
import com.sparkfusion.portdomainservices.admin.portsections.usecase.IReadSectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SectionsListViewModel @Inject constructor(
    private val readSectionsUseCase: IReadSectionsUseCase
): ViewModel() {

    private val _readingState = MutableStateFlow<ReadingState>(ReadingState.Initial)
    val readingState: StateFlow<ReadingState> = _readingState.asStateFlow()

    fun read() {
        if (readingState.value == ReadingState.Progress) return

        _readingState.update { ReadingState.Progress }
        viewModelScope.launch {
            readSectionsUseCase.readSections()
                .onSuccess { data ->
                    _readingState.update { ReadingState.Success(data) }
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
        data class Success(val data: List<ReadSectionModel>) : ReadingState
    }
}































