package com.sparkfusion.services.admin.practice.viewmodel

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.core.common.image.BitmapToFileWorker
import com.sparkfusion.core.common.image.ImageChildren
import com.sparkfusion.portdomainservices.admin.portpractice.model.CreatePracticeModel
import com.sparkfusion.portdomainservices.admin.portpractice.model.InformationBlockModel
import com.sparkfusion.portdomainservices.admin.portpractice.model.RelocationModel
import com.sparkfusion.portdomainservices.admin.portpractice.usecase.ICreatePracticeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PracticeAddingViewModel @Inject constructor(
    private val createPracticeUseCase: ICreatePracticeUseCase,
    private val bitmapToFileWorker: BitmapToFileWorker
) : ViewModel() {

    private val _creatingState = MutableStateFlow<CreatingState>(CreatingState.Initial)
    val creatingState: StateFlow<CreatingState> = _creatingState.asStateFlow()

    private val _checkState = MutableStateFlow<CheckState>(CheckState.Initial)
    val checkState: StateFlow<CheckState> = _checkState.asStateFlow()

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    fun create() {
        if (creatingState.value == CreatingState.Progress) return

        if (state.value.bitmap == null) {
            _checkState.update { CheckState.ImageNotSelected }
            return
        }

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

        _creatingState.update { CreatingState.Progress }
        viewModelScope.launch {
            val model = CreatePracticeModel(
                payAbility = state.value.payAbility,
                description = state.value.description,
                workType = if (state.value.workTypeRemote && state.value.workTypeOffline) 3
                else if (state.value.workTypeRemote) 1
                else 2,
                title = state.value.title,
                website = state.value.website,
                informationBlocks = state.value.informationBlocks,
                relocations = state.value.relocations
            )
            createPracticeUseCase.createPractice(
                model, bitmapToFileWorker.invoke(state.value.bitmap!!, ImageChildren.PRACTICE_ICON)
            ).onSuccess {
                _creatingState.update { CreatingState.Success }
            }.onFailure {
                Log.d("TAGTAG", it.message.toString())
                _creatingState.update { CreatingState.Error }
            }
        }
    }

    fun clearCreatingState() {
        _creatingState.update { CreatingState.Initial }
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
        val bitmap: Bitmap? = null,
        val payAbility: Boolean = false,
        val description: String = "",
        val workTypeRemote: Boolean = false,
        val workTypeOffline: Boolean = false,
        val title: String = "",
        val website: String = "",
        val informationBlocks: Set<InformationBlockModel> = emptySet(),
        val relocations: Set<RelocationModel> = emptySet()
    )

    sealed interface CheckState {
        data object Initial : CheckState
        data object ImageNotSelected : CheckState
        data object TitleEmpty : CheckState
        data object WorkTypeNotSelected : CheckState
        data object DescriptionEmpty : CheckState
    }

    sealed interface CreatingState {
        data object Initial : CreatingState
        data object Progress : CreatingState
        data object Error : CreatingState
        data object Success : CreatingState
    }
}






















