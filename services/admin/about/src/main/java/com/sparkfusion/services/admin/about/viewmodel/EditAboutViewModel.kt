package com.sparkfusion.services.admin.about.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.core.common.image.BitmapToFileWorker
import com.sparkfusion.core.common.image.ImageChildren
import com.sparkfusion.portdomainservices.admin.portabout.IReadAboutBlocksUseCase
import com.sparkfusion.portdomainservices.admin.portabout.IUpdateAboutBlockUseCase
import com.sparkfusion.portdomainservices.admin.portabout.UpdateAboutModel
import com.sparkfusion.services.admin.about.model.EditAboutBlockModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class EditAboutViewModel @Inject constructor(
    private val readAboutBlocksUseCase: IReadAboutBlocksUseCase,
    private val updateAboutBlockUseCase: IUpdateAboutBlockUseCase,
    private val bitmapToFileWorker: BitmapToFileWorker
) : ViewModel() {

    internal var currentImageItem: EditAboutBlockModel? = null

    private val _readState = MutableStateFlow<ReadState>(ReadState.Initial)
    internal val readState: StateFlow<ReadState> = _readState.asStateFlow()

    private val _updateState = MutableStateFlow<UpdateState>(UpdateState.Initial)
    internal val updateState: StateFlow<UpdateState> = _updateState.asStateFlow()

    internal fun readBlocks() {
        if (readState.value is ReadState.Success) return
        if (readState.value == ReadState.Progress) return

        _readState.update { ReadState.Progress }
        viewModelScope.launch {
            readAboutBlocksUseCase.readAboutBlock()
                .onSuccess { list ->
                    val data = list.map { EditAboutBlockModel(it.id, it.description, it.icon) }
                    _readState.update { ReadState.Success(data) }
                }
                .onFailure {
                    _readState.update { ReadState.Error }
                }
        }
    }

    internal fun updateBlocks() {
        val state = readState.value
        if (state is ReadState.Success) {
            if (updateState.value == UpdateState.Success) return

            _updateState.update { UpdateState.Progress }
            viewModelScope.launch {
                val data = state.data.map {
                    val file = it.bitmap?.let { bitmap ->
                        bitmapToFileWorker.invoke(bitmap, ImageChildren.ABOUT_ICON)
                    }
                    UpdateAboutModel(it.id, it.description, it.icon, file)
                }

                updateAboutBlockUseCase.updateBlock(data)
                    .onSuccess {
                        _updateState.update { UpdateState.Success }
                    }
                    .onFailure {
                        _updateState.update { UpdateState.Error }
                    }
            }
        }
    }

    internal fun addBlock() {
        if (readState.value is ReadState.Success) {
            val oldData = (readState.value as ReadState.Success).data
            val newData = oldData.toMutableList()
            newData.add(EditAboutBlockModel(null, null, null))

            _readState.update { ReadState.Success(newData.toList()) }
        }
    }

    internal fun updateDescription(block: EditAboutBlockModel, value: String) {
        if (readState.value is ReadState.Success) {
            val data = (readState.value as ReadState.Success).data.toMutableList()
            val index = data.indexOf(block)
            data[index] = block.copy(description = value)

            _readState.update { ReadState.Success(data.toList()) }
        }
    }

    internal fun updateImage(bitmap: Bitmap?) {
        if (bitmap == null || currentImageItem == null) return
        if (readState.value is ReadState.Success) {
            val data = (readState.value as ReadState.Success).data.toMutableList()
            val index = data.indexOf(currentImageItem)
            data[index] = currentImageItem!!.copy(bitmap = bitmap)

            _readState.update { ReadState.Success(data.toList()) }
        }

        currentImageItem = null
    }

    internal fun clearUpdateState() {
        _updateState.update { UpdateState.Initial }
    }

    internal fun clearReadingState() {
        _readState.update { ReadState.Initial }
    }

    internal sealed interface UpdateState {
        data object Initial : UpdateState
        data object Error : UpdateState
        data object Progress : UpdateState
        data object Success : UpdateState
    }

    internal sealed interface ReadState {
        data object Initial : ReadState
        data object Error : ReadState
        data object Progress : ReadState
        data class Success(val data: List<EditAboutBlockModel>) : ReadState
    }
}


























