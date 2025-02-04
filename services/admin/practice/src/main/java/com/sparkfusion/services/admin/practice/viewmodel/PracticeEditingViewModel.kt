package com.sparkfusion.services.admin.practice.viewmodel

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.core.common.image.BitmapToFileWorker
import com.sparkfusion.core.common.image.ImageChildren
import com.sparkfusion.portdomainservices.admin.portpractice.model.UpdatePracticeModel
import com.sparkfusion.portdomainservices.admin.portpractice.usecase.IReadPracticeByIdUseCase
import com.sparkfusion.portdomainservices.admin.portpractice.usecase.IUpdatePracticeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PracticeEditingViewModel @Inject constructor(
    private val editPracticeUseCase: IUpdatePracticeUseCase,
    private val readPracticeByIdUseCase: IReadPracticeByIdUseCase,
    private val bitmapToFileWorker: BitmapToFileWorker
) : ViewModel() {

    private val _updatingState = MutableStateFlow<UpdatingState>(UpdatingState.Initial)
    val updatingState: StateFlow<UpdatingState> = _updatingState.asStateFlow()

    private val _checkState = MutableStateFlow<CheckState>(CheckState.Initial)
    val checkState: StateFlow<CheckState> = _checkState.asStateFlow()

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    private val _readingState = MutableStateFlow<ReadingState>(ReadingState.Initial)
    val readingState: StateFlow<ReadingState> = _readingState.asStateFlow()

    fun read(id: Int) {
        if (readingState.value == ReadingState.Success) return
        if (readingState.value == ReadingState.Progress) return

        _readingState.update { ReadingState.Progress }
        viewModelScope.launch {
            readPracticeByIdUseCase.readPracticeById(id)
                .onSuccess { model ->
                    _state.update {
                        State(
                            id = model.id,
                            icon = model.icon,
                            payAbility = model.payAbility,
                            description = model.description,
                            workTypeRemote = model.workType == 1 || model.workType == 3,
                            workTypeOffline = model.workType == 2 || model.workType == 3,
                            title = model.title,
                            website = model.website ?: ""
                        )
                    }
                    _readingState.update { ReadingState.Success }
                }
                .onFailure {
                    _readingState.update { ReadingState.Error }
                }
        }
    }

    fun update() {
        if (readingState.value != ReadingState.Success) return
        if (updatingState.value == UpdatingState.Progress) return

        if (state.value.title.isEmpty()) {
            _checkState.update { CheckState.TitleEmpty }
            return
        }

        if (state.value.description.isEmpty()) {
            _checkState.update { CheckState.DescriptionEmpty }
            return
        }

        if (!state.value.workTypeRemote && !state.value.workTypeOffline) {
            _checkState.update { CheckState.WorkTypeNotSelected }
            return
        }

        _updatingState.update { UpdatingState.Progress }
        viewModelScope.launch {
            val model = UpdatePracticeModel(
                id = state.value.id,
                icon = state.value.icon,
                payAbility = state.value.payAbility,
                description = state.value.description,
                workType = if (state.value.workTypeRemote && state.value.workTypeOffline) 3
                else if (state.value.workTypeRemote) 1
                else 2,
                title = state.value.title,
                website = state.value.website,
                informationBlocks = emptySet(),
                relocations = emptySet()
            )
            editPracticeUseCase.updatePractice(
                model,
                state.value.bitmap?.let {
                    bitmapToFileWorker.invoke(it, ImageChildren.PRACTICE_ICON)
                }
            ).onSuccess {
                _updatingState.update { UpdatingState.Success }
            }.onFailure {
                Log.d("TAGTAG", it.message.toString())
                _updatingState.update { UpdatingState.Error }
            }
        }
    }

    fun clearReadingState() {
        _readingState.update { ReadingState.Initial }
    }

    fun clearUpdatingState() {
        _updatingState.update { UpdatingState.Initial }
    }

    fun clearCheckState() {
        _checkState.update { CheckState.Initial }
    }

    fun updateBitmap(bitmap: Bitmap?) {
        if (bitmap == null) return
        _state.update { it.copy(bitmap = bitmap) }
    }

    fun updatePayAbility(value: Boolean) {
        _state.update { it.copy(payAbility = value) }
    }

    fun updateDescription(value: String) {
        _state.update { it.copy(description = value) }
    }

    fun workTypeRemote(value: Boolean) {
        _state.update { it.copy(workTypeRemote = value) }
    }

    fun workTypeOffline(value: Boolean) {
        _state.update { it.copy(workTypeOffline = value) }
    }

    fun updateTitle(value: String) {
        _state.update { it.copy(title = value) }
    }

    fun updateWebsite(value: String) {
        _state.update { it.copy(website = value) }
    }

    data class State(
        val id: Int? = null,
        val icon: String? = null,
        val bitmap: Bitmap? = null,
        val payAbility: Boolean = false,
        val description: String = "",
        val workTypeRemote: Boolean = false,
        val workTypeOffline: Boolean = false,
        val title: String = "",
        val website: String = ""
    )

    sealed interface ReadingState {
        data object Initial : ReadingState
        data object Error : ReadingState
        data object Progress : ReadingState
        data object Success : ReadingState
    }

    sealed interface CheckState {
        data object Initial : CheckState
        data object TitleEmpty : CheckState
        data object WorkTypeNotSelected : CheckState
        data object DescriptionEmpty : CheckState
    }

    sealed interface UpdatingState {
        data object Initial : UpdatingState
        data object Progress : UpdatingState
        data object Error : UpdatingState
        data object Success : UpdatingState
    }
}
























