package com.sparkfusion.services.admin.sections.viewmodel

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.core.common.image.BitmapToFileWorker
import com.sparkfusion.core.common.image.ImageChildren
import com.sparkfusion.portdomainservices.admin.portsections.model.CreateSectionModel
import com.sparkfusion.portdomainservices.admin.portsections.usecase.ICreateSectionUseCase
import com.sparkfusion.services.admin.sections.viewmodel.EditSectionViewModel.CheckState
import com.sparkfusion.services.admin.sections.viewmodel.EditSectionViewModel.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddSectionViewModel @Inject constructor(
    private val addSectionUseCase: ICreateSectionUseCase,
    private val bitmapToFileWorker: BitmapToFileWorker
): ViewModel() {

    private val _creationState = MutableStateFlow<CreationState>(CreationState.Initial)
    val creationState: StateFlow<CreationState> = _creationState.asStateFlow()

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    private val _checkState = MutableStateFlow<CheckState>(CheckState.Initial)
    val checkState: StateFlow<CheckState> = _checkState.asStateFlow()

    fun create() {
        if (creationState.value == CreationState.Progress) return

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

        if (state.value.bitmap == null) {
            _checkState.update { CheckState.ImageNotSelected }
        }

        _creationState.update { CreationState.Progress }
        viewModelScope.launch {
            val model = CreateSectionModel(
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
                toCourse = state.value.toCourse
            )
            addSectionUseCase.createSection(
                model,
                bitmapToFileWorker.invoke(state.value.bitmap!!, ImageChildren.SECTION_ICON)
            ).onSuccess {
                _creationState.update { CreationState.Success }
            }.onFailure {
                Log.d("TAGTAG", it.message.toString())
                _creationState.update { CreationState.Error }
            }
        }
    }

    fun clearCheckState() {
        _checkState.update { CheckState.Initial }
    }

    fun clearCreatingState() {
        _creationState.update { CreationState.Initial }
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
        data object ImageNotSelected : CheckState
    }

    sealed interface CreationState {
        data object Initial : CreationState
        data object Progress : CreationState
        data object Error : CreationState
        data object Success : CreationState
    }
}

























