package com.sparkfusion.features.pupil.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.domain.pupil.port.porthome.model.ReadInstitutionEventModel
import com.sparkfusion.domain.pupil.port.porthome.usecase.IReadEventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val readEventsUseCase: IReadEventsUseCase
): ViewModel() {

    private val _institutionEventState =
        MutableStateFlow<InstitutionEventState>(InstitutionEventState.Initial)
    internal val institutionEventState: StateFlow<InstitutionEventState> =
        _institutionEventState.asStateFlow()

    internal fun readEvents() {
        if (institutionEventState.value is InstitutionEventState.Success) return
        if (institutionEventState.value == InstitutionEventState.Progress) return

        _institutionEventState.update { InstitutionEventState.Progress }
        viewModelScope.launch {
            readEventsUseCase.readEvents()
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

    internal sealed interface InstitutionEventState {
        data object Initial : InstitutionEventState
        data object Progress : InstitutionEventState
        data object Error : InstitutionEventState
        data class Success(val data: List<ReadInstitutionEventModel>) : InstitutionEventState
    }
}




























