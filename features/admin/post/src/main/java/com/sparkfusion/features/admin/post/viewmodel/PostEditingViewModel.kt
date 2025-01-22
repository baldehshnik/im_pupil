package com.sparkfusion.features.admin.post.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.core.common.image.BitmapToFileWorker
import com.sparkfusion.core.common.image.ImageChildren
import com.sparkfusion.domain.admin.port.portpost.IReadInstitutionEventUseCase
import com.sparkfusion.domain.admin.port.portpost.IUpdateInstitutionEventUseCase
import com.sparkfusion.domain.admin.port.portpost.UpdateInstitutionEventModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostEditingViewModel @Inject constructor(
    private val updateInstitutionEventUseCase: IUpdateInstitutionEventUseCase,
    private val readInstitutionEventUseCase: IReadInstitutionEventUseCase,
    private val bitmapToFileWorker: BitmapToFileWorker
) : ViewModel() {

    private val _updateState = MutableStateFlow<UpdateState>(UpdateState.Initial)
    val updateState: StateFlow<UpdateState> = _updateState.asStateFlow()

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    private val _updatingCheckState =
        MutableStateFlow<UpdatingCheckState>(UpdatingCheckState.Initial)
    val updatingCheckState: StateFlow<UpdatingCheckState> = _updatingCheckState.asStateFlow()

    private val _readingState = MutableStateFlow<ReadingState>(ReadingState.Initial)
    val readingState: StateFlow<ReadingState> = _readingState.asStateFlow()

    fun readEvent(id: Int) {
        if (readingState.value == ReadingState.Progress) return

        _readingState.update { ReadingState.Progress }
        viewModelScope.launch {
            readInstitutionEventUseCase.readInstitutionEvent(id)
                .onSuccess { model ->
                    _state.update {
                        it.copy(
                            id = model.id,
                            initialImageUrl = model.image,
                            title = model.title,
                            description = model.description,
                            type = model.type,
                            duration = model.duration
                        )
                    }
                    _readingState.update { ReadingState.Success }
                }
                .onFailure {
                    _readingState.update { ReadingState.Error }
                }
        }
    }

    fun updateEvent() {
        if (updateState.value == UpdateState.Progress) return

        if (state.value.title.length < 6) {
            _updatingCheckState.update { UpdatingCheckState.TitleTooShort }
            return
        }

        if (state.value.title.length > 32) {
            _updatingCheckState.update { UpdatingCheckState.TitleTooLong }
            return
        }

        if (state.value.description.isEmpty()) {
            _updatingCheckState.update { UpdatingCheckState.DescriptionTooShort }
            return
        }

        if (state.value.type == -1) {
            _updatingCheckState.update { UpdatingCheckState.TypeNotSelected }
            return
        }

        if (state.value.duration < 0) {
            _updatingCheckState.update { UpdatingCheckState.DurationNotSelected }
            return
        }

        _updateState.update { UpdateState.Progress }
        viewModelScope.launch {
            val model = UpdateInstitutionEventModel(
                state.value.id,
                state.value.title,
                state.value.description,
                state.value.initialImageUrl,
                state.value.duration,
                state.value.type
            )
            updateInstitutionEventUseCase.updateInstitutionEvent(
                model,
                if (state.value.image == null) null else bitmapToFileWorker.invoke(
                    state.value.image!!,
                    ImageChildren.EVENT_ICON
                )
            ).onSuccess {
                _updateState.update { UpdateState.Success }
            }.onFailure {
                _updateState.update { UpdateState.Error }
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

    fun clearUpdatingCheckState() {
        _updatingCheckState.update { UpdatingCheckState.Initial }
    }

    fun clearUpdatingState() {
        _updateState.update { UpdateState.Initial }
    }

    fun clearReadingState() {
        _readingState.update { ReadingState.Initial }
    }

    data class State(
        val id: Int = -1,
        val image: Bitmap? = null,
        val initialImageUrl: String = "",
        val title: String = "",
        val description: String = "",
        val type: Int = 1,
        val duration: Int = 0
    )

    sealed interface UpdatingCheckState {
        data object Initial : UpdatingCheckState
        data object TitleTooShort : UpdatingCheckState
        data object DescriptionTooShort : UpdatingCheckState
        data object TypeNotSelected : UpdatingCheckState
        data object DurationNotSelected : UpdatingCheckState
        data object TitleTooLong : UpdatingCheckState
    }

    sealed interface UpdateState {
        data object Initial : UpdateState
        data object Error : UpdateState
        data object Progress : UpdateState
        data object Success : UpdateState
    }

    sealed interface ReadingState {
        data object Initial : ReadingState
        data object Error : ReadingState
        data object Progress : ReadingState
        data object Success : ReadingState
    }
}




























