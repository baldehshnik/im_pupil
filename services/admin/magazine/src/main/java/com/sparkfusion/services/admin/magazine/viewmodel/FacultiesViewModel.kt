package com.sparkfusion.services.admin.magazine.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.admin.portmagazine.model.FacultyModel
import com.sparkfusion.portdomainservices.admin.portmagazine.usecase.IReadFacultiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FacultiesViewModel @Inject constructor(
    private val readFacultiesUseCase: IReadFacultiesUseCase
) : ViewModel() {

    private val _readingState = MutableStateFlow<ReadingState>(ReadingState.Initial)
    val readingState: StateFlow<ReadingState> = _readingState.asStateFlow()

    fun read() {
        if (readingState.value == ReadingState.Progress) return

        _readingState.update { ReadingState.Progress }
        viewModelScope.launch {
            readFacultiesUseCase.readFaculties()
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
        data object Error : ReadingState
        data object Progress : ReadingState
        data class Success(val data: List<FacultyModel>) : ReadingState
    }
}

























