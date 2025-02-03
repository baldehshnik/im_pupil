package com.sparkfusion.services.admin.sections.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.admin.portsections.model.ReadSectionModel
import com.sparkfusion.portdomainservices.admin.portsections.usecase.IDeleteSectionByIdUseCase
import com.sparkfusion.portdomainservices.admin.portsections.usecase.IReadSectionByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SectionsDetailsViewModel @Inject constructor(
    private val readSectionByIdUseCase: IReadSectionByIdUseCase,
    private val deleteSectionByIdUseCase: IDeleteSectionByIdUseCase
): ViewModel() {

    private val _readingState = MutableStateFlow<ReadingState>(ReadingState.Initial)
    val readingState: StateFlow<ReadingState> = _readingState.asStateFlow()

    private val _deletingState = MutableStateFlow<DeletingState>(DeletingState.Initial)
    val deletingState: StateFlow<DeletingState> = _deletingState.asStateFlow()

    fun delete(id: Int) {
        if (deletingState.value == DeletingState.Progress) return

        _deletingState.update { DeletingState.Progress }
        viewModelScope.launch {
            deleteSectionByIdUseCase.deleteSectionById(id)
                .onSuccess {
                    _deletingState.update { DeletingState.Success }
                }
                .onFailure {
                    _deletingState.update { DeletingState.Error }
                }
        }
    }

    fun read(id: Int) {
        if (readingState.value == ReadingState.Progress) return

        _readingState.update { ReadingState.Progress }
        viewModelScope.launch {
            readSectionByIdUseCase.readSectionById(id)
                .onSuccess { model ->
                    _readingState.update { ReadingState.Success(model) }
                }
                .onFailure {
                    _readingState.update { ReadingState.Error }
                }
        }
    }

    fun clearDeletingState() {
        _deletingState.update { DeletingState.Initial }
    }

    fun clearReadingState() {
        _readingState.update { ReadingState.Initial }
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
        data class Success(val model: ReadSectionModel) : ReadingState
    }
}



















