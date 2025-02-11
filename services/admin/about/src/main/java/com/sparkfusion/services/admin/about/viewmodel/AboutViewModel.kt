package com.sparkfusion.services.admin.about.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.portdomainservices.admin.portabout.AboutModel
import com.sparkfusion.portdomainservices.admin.portabout.IReadAboutBlocksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AboutViewModel @Inject constructor(
    private val readAboutBlocksUseCase: IReadAboutBlocksUseCase
) : ViewModel() {

    internal var reload: Boolean = false

    private val _readState = MutableStateFlow<ReadState>(ReadState.Initial)
    internal val readState: StateFlow<ReadState> = _readState.asStateFlow()

    internal fun readBlocks() {
        if (reload) reload = false else return
        if (readState.value == ReadState.Progress) return

        _readState.update { ReadState.Progress }
        viewModelScope.launch {
            readAboutBlocksUseCase.readAboutBlock()
                .onSuccess { list ->
                    _readState.update { ReadState.Success(list) }
                }
                .onFailure {
                    _readState.update { ReadState.Error }
                }
        }
    }

    internal sealed interface ReadState {
        data object Initial : ReadState
        data object Error : ReadState
        data object Progress : ReadState
        data class Success(val data: List<AboutModel>) : ReadState
    }
}























