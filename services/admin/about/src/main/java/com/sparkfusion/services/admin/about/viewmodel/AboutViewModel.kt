package com.sparkfusion.services.admin.about.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.admin.portabout.AboutModel
import com.sparkfusion.portdomainservices.admin.portabout.IReadAboutBlocksUseCase
import com.sparkfusion.services.admin.about.model.EditAboutBlockModel
import com.sparkfusion.services.admin.about.viewmodel.EditAboutViewModel.ReadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(
    private val readAboutBlocksUseCase: IReadAboutBlocksUseCase
) : ViewModel() {

    private val _readState = MutableStateFlow<ReadState>(ReadState.Initial)
    val readState: StateFlow<ReadState> = _readState.asStateFlow()

    fun readBlocks() {
        if (readState.value == ReadState.Progress) return

        _readState.update { ReadState.Progress }
        viewModelScope.launch {
            readAboutBlocksUseCase.readAboutBlock()
                .onSuccess { list ->
                    _readState.update { ReadState.Success(list) }
                }
                .onFailure {
                    Log.d("TAGTAG", "ABOUT - " + it.message.toString())
                    _readState.update { ReadState.Error }
                }
        }
    }

    sealed interface ReadState {
        data object Initial : ReadState
        data object Error : ReadState
        data object Progress : ReadState
        data class Success(val data: List<AboutModel>) : ReadState
    }
}























