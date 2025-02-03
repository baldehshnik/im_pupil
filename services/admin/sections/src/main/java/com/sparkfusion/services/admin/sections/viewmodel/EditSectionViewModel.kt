package com.sparkfusion.services.admin.sections.viewmodel

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.core.common.image.BitmapToFileWorker
import com.sparkfusion.core.common.image.ImageChildren
import com.sparkfusion.portdomainservices.admin.portsections.model.UpdateSectionModel
import com.sparkfusion.portdomainservices.admin.portsections.usecase.IReadSectionByIdUseCase
import com.sparkfusion.portdomainservices.admin.portsections.usecase.IUpdateSectionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditSectionViewModel @Inject constructor(
    private val readSectionByIdUseCase: IReadSectionByIdUseCase,
    private val updateSectionUseCase: IUpdateSectionUseCase,
    private val bitmapToFileWorker: BitmapToFileWorker
) : ViewModel() {

    private val _readingState = MutableStateFlow<ReadingState>(ReadingState.Initial)
    val readingState: StateFlow<ReadingState> = _readingState.asStateFlow()

    private val _updatingState = MutableStateFlow<UpdatingState>(UpdatingState.Initial)
    val updatingState: StateFlow<UpdatingState> = _updatingState.asStateFlow()

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    private val _checkState = MutableStateFlow<CheckState>(CheckState.Initial)
    val checkState: StateFlow<CheckState> = _checkState.asStateFlow()

    fun read(id: Int) {
        if (readingState.value == ReadingState.Success) return
        if (readingState.value == ReadingState.Progress) return

        _readingState.update { ReadingState.Progress }
        viewModelScope.launch {
            readSectionByIdUseCase.readSectionById(id)
                .onSuccess { model ->
                    _state.update {
                        State(
                            id = model.id,
                            icon = model.icon,
                            title = model.title,
                            trainer = model.trainer,
                            price = model.price,
                            genderMen = model.gender == 1 || model.gender == 3,
                            genderWomen = model.gender == 2 || model.gender == 3,
                            description = model.description ?: "",
                            fromCourse = model.fromCourse ?: 1,
                            toCourse = model.toCourse ?: 6
                        )
                    }
                    _readingState.update { ReadingState.Success }
                }
                .onFailure {
                    Log.d("TAGTAG", it.message.toString())
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

        if (state.value.trainer.isEmpty()) {
            _checkState.update { CheckState.TrainerEmpty }
            return
        }

        if (!state.value.genderMen && !state.value.genderWomen) {
            _checkState.update { CheckState.GenderNotSelected }
            return
        }

        _updatingState.update { UpdatingState.Progress }
        viewModelScope.launch {
            val model = UpdateSectionModel(
                id = state.value.id,
                title = state.value.title,
                trainer = state.value.trainer,
                price = state.value.price,
                gender = when {
                    state.value.genderMen && state.value.genderWomen -> 3
                    state.value.genderMen -> 1
                    state.value.genderWomen -> 2
                    else -> 3
                },
                description = state.value.description,
                fromCourse = state.value.fromCourse,
                toCourse = state.value.toCourse,
                icon = state.value.icon
            )
            updateSectionUseCase.updateSection(
                model,
                state.value.bitmap?.let { bitmapToFileWorker(it, ImageChildren.SECTION_ICON) }
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

    fun updateTitle(value: String) {
        _state.update { it.copy(title = value) }
    }

    fun updateTrainer(value: String) {
        _state.update { it.copy(trainer = value) }
    }

    fun updatePrice(value: Boolean) {
        _state.update { it.copy(price = value) }
    }

    fun updateGenderMen(value: Boolean) {
        _state.update { it.copy(genderMen = value) }
    }

    fun updateGenderWomen(value: Boolean) {
        _state.update { it.copy(genderWomen = value) }
    }

    fun updateDescription(value: String) {
        _state.update { it.copy(description = value) }
    }

    fun updateFromCourse(value: Int) {
        _state.update { it.copy(fromCourse = value) }
    }

    fun updateToCourse(value: Int) {
        _state.update { it.copy(toCourse = value) }
    }

    data class State(
        val id: Int = -1,
        val icon: String? = "",
        val bitmap: Bitmap? = null,
        val title: String = "",
        val trainer: String = "",
        val price: Boolean = false,
        val genderMen: Boolean = false,
        val genderWomen: Boolean = false,
        val description: String = "",
        val fromCourse: Int = 1,
        val toCourse: Int = 6
    )

    sealed interface CheckState {
        data object Initial : CheckState
        data object TitleEmpty : CheckState
        data object TrainerEmpty : CheckState
        data object GenderNotSelected : CheckState
    }

    sealed interface UpdatingState {
        data object Initial : UpdatingState
        data object Progress : UpdatingState
        data object Error : UpdatingState
        data object Success : UpdatingState
    }

    sealed interface ReadingState {
        data object Initial : ReadingState
        data object Progress : ReadingState
        data object Error : ReadingState
        data object Success : ReadingState
    }
}
























