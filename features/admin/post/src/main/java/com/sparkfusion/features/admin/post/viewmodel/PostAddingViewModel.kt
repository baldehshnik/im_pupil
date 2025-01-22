package com.sparkfusion.features.admin.post.viewmodel

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.core.common.image.BitmapToFileWorker
import com.sparkfusion.core.common.image.ImageChildren
import com.sparkfusion.domain.admin.port.portpost.AddInstitutionEventModel
import com.sparkfusion.domain.admin.port.portpost.IAddInstitutionEventUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostAddingViewModel @Inject constructor(
    private val addInstitutionEventUseCase: IAddInstitutionEventUseCase,
    private val bitmapToFileWorker: BitmapToFileWorker
) : ViewModel() {

    private val _addingState = MutableStateFlow<AddingState>(AddingState.Initial)
    val addingState: StateFlow<AddingState> = _addingState.asStateFlow()

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    private val _addingCheckState = MutableStateFlow<AddingCheckState>(AddingCheckState.Initial)
    val addingCheckState: StateFlow<AddingCheckState> = _addingCheckState.asStateFlow()

    fun saveEvent() {
        Log.d("TAGTAG", "here")
        if (addingState.value == AddingState.Progress) return

        if (state.value.image == null) {
            _addingCheckState.update { AddingCheckState.ImageNotSelected }
            return
        }

        if (state.value.title.length < 6) {
            _addingCheckState.update { AddingCheckState.TitleTooShort }
            return
        }

        if (state.value.title.length > 32) {
            _addingCheckState.update { AddingCheckState.TitleTooLong }
            return
        }

        if (state.value.description.isEmpty()) {
            _addingCheckState.update { AddingCheckState.DescriptionTooShort }
            return
        }

        if (state.value.type == -1) {
            _addingCheckState.update { AddingCheckState.TypeNotSelected }
            return
        }

        if (state.value.duration < 0) {
            _addingCheckState.update { AddingCheckState.DurationNotSelected }
            return
        }

        _addingState.update { AddingState.Progress }
        viewModelScope.launch {
            val model = AddInstitutionEventModel(
                state.value.title,
                state.value.description,
                state.value.duration,
                state.value.type
            )
            addInstitutionEventUseCase.addInstitutionEvent(
                model, bitmapToFileWorker.invoke(state.value.image!!, ImageChildren.EVENT_ICON)
            ).onSuccess {
                _addingState.update { AddingState.Success }
            }.onFailure {
                _addingState.update { AddingState.Error }
            }
        }
    }

    fun updateTitle(value: String) {
        _state.update { it.copy(title = value) }
    }

    fun updateDescription(value: String) {
        _state.update { it.copy(description = value) }
    }

    fun updateType(value: Int) {
        _state.update { it.copy(type = value) }
    }

    fun updateDuration(value: Int) {
        _state.update { it.copy(duration = value) }
    }

    fun updateImage(bitmap: Bitmap?) {
        _state.update { it.copy(image = bitmap) }
    }

    fun clearAddingCheckState() {
        _addingCheckState.update { AddingCheckState.Initial }
    }

    fun clearAddingState() {
        _addingState.update { AddingState.Initial }
    }

    data class State(
        val image: Bitmap? = null,
        val title: String = "",
        val description: String = "",
        val type: Int = 1,
        val duration: Int = 0
    )

    sealed interface AddingCheckState {
        data object Initial : AddingCheckState
        data object TitleTooShort : AddingCheckState
        data object DescriptionTooShort : AddingCheckState
        data object TypeNotSelected : AddingCheckState
        data object DurationNotSelected : AddingCheckState
        data object ImageNotSelected : AddingCheckState
        data object TitleTooLong : AddingCheckState
    }

    sealed interface AddingState {
        data object Initial : AddingState
        data object Error : AddingState
        data object Progress : AddingState
        data object Success : AddingState
    }
}






















