package com.sparkfusion.features.admin.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.domain.admin.port.porthome.IReadInstitutionEventsUseCase
import com.sparkfusion.domain.admin.port.porthome.InstitutionEventModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val readInstitutionEventsUseCase: IReadInstitutionEventsUseCase
) : ViewModel() {

    private val _institutionEventState =
        MutableStateFlow<InstitutionEventState>(InstitutionEventState.Initial)
    val institutionEventState: StateFlow<InstitutionEventState> =
        _institutionEventState.asStateFlow()

    fun readEvents() {
        _institutionEventState.update { InstitutionEventState.Progress }
        viewModelScope.launch(ioDispatcher) {
            readInstitutionEventsUseCase.readInstitutionEvents()
                .onSuccess { list ->
                    Log.d("TAGTAG", "list - " + list.joinToString())
                    _institutionEventState.update { InstitutionEventState.Success(list) }
                }
                .onFailure {
                    Log.d("TAGTAG", it.message.toString())
                    _institutionEventState.update { InstitutionEventState.Error }
                }
        }
    }

    sealed interface InstitutionEventState {
        data object Initial : InstitutionEventState
        data object Progress : InstitutionEventState
        data object Error : InstitutionEventState
        data class Success(val data: List<InstitutionEventModel>) : InstitutionEventState
    }
}


















