package com.sparkfusion.services.admin.practice.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.admin.portpractice.model.ReadPracticeModel
import com.sparkfusion.portdomainservices.admin.portpractice.usecase.IDeletePracticeByIdUseCase
import com.sparkfusion.portdomainservices.admin.portpractice.usecase.IReadPracticeByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PracticeDetailsViewModel @Inject constructor(
    private val readPracticeByIdUseCase: IReadPracticeByIdUseCase,
    private val deletePracticeByIdUseCase: IDeletePracticeByIdUseCase
) : ViewModel() {

    private val _readingState = MutableStateFlow<ReadingState>(ReadingState.Initial)
    val readingState: StateFlow<ReadingState> = _readingState.asStateFlow()

    private val _deletingState = MutableStateFlow<DeletingState>(DeletingState.Initial)
    val deletingState: StateFlow<DeletingState> = _deletingState.asStateFlow()

    fun read(id: Int) {
        if (readingState.value == ReadingState.Progress) return

        _readingState.update { ReadingState.Progress }
        viewModelScope.launch {
            readPracticeByIdUseCase.readPracticeById(id)
                .onSuccess { model ->
                    _readingState.update { ReadingState.Success(model) }
                }
                .onFailure {
                    Log.d("TAGTAG", it.message.toString())
                    _readingState.update { ReadingState.Error }
                }
        }
    }

    fun delete(id: Int) {
        if (deletingState.value == DeletingState.Progress) return

        _deletingState.update { DeletingState.Progress }
        viewModelScope.launch {
            deletePracticeByIdUseCase.deletePracticeById(id)
                .onSuccess {
                    _deletingState.update { DeletingState.Success }
                }
                .onFailure {
                    _deletingState.update { DeletingState.Error }
                }
        }
    }

    fun clearReadingState() {
        _readingState.update { ReadingState.Initial }
    }

    fun clearDeletingState() {
        _deletingState.update { DeletingState.Initial }
    }

    sealed interface DeletingState {
        data object Initial : DeletingState
        data object Progress : DeletingState
        data object Error : DeletingState
        data object Success : DeletingState
    }

    sealed interface ReadingState {
        data object Initial : ReadingState
        data object Progress : ReadingState
        data object Error : ReadingState
        data class Success(val model: ReadPracticeModel) : ReadingState
    }
}


























