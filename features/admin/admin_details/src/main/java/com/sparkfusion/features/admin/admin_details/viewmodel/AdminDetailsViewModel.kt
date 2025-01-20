package com.sparkfusion.features.admin.admin_details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.domain.admin.port.portadmindetails.AdminDetailsModel
import com.sparkfusion.domain.admin.port.portadmindetails.IDeleteAdminUseCase
import com.sparkfusion.domain.admin.port.portadmindetails.IReadAdminDetailsUseCase
import com.sparkfusion.domain.admin.port.portadmindetails.IUpdateAdminAccessUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminDetailsViewModel @Inject constructor(
    private val readAdminDetailsUseCase: IReadAdminDetailsUseCase,
    private val updateAdminAccessUseCase: IUpdateAdminAccessUseCase,
    private val deleteAdminUseCase: IDeleteAdminUseCase
) : ViewModel() {

    private val _adminDetailsState =
        MutableStateFlow<ReadAdminDetailsState>(ReadAdminDetailsState.Initial)
    val adminDetailsState: StateFlow<ReadAdminDetailsState> = _adminDetailsState.asStateFlow()

    private val _adminAccessState =
        MutableStateFlow<UpdateAdminAccessState>(UpdateAdminAccessState.Initial)
    val adminAccessState: StateFlow<UpdateAdminAccessState> = _adminAccessState.asStateFlow()

    private val _adminDeletingState = MutableStateFlow<DeleteAdminState>(DeleteAdminState.Initial)
    val adminDeletingState: StateFlow<DeleteAdminState> = _adminDeletingState.asStateFlow()

    fun readAdminDetails(id: Int) {
        _adminDetailsState.update { ReadAdminDetailsState.Progress }
        viewModelScope.launch {
            readAdminDetailsUseCase.readAdminDetailsById(id)
                .onSuccess { model ->
                    _adminDetailsState.update { ReadAdminDetailsState.Success(model) }
                }
                .onFailure {
                    _adminDetailsState.update { ReadAdminDetailsState.Error }
                }
        }
    }

    fun updateAdminAccess() {
        val state = adminDetailsState.value
        if (state is ReadAdminDetailsState.Success) {
            if (adminAccessState.value == UpdateAdminAccessState.Progress) return

            _adminAccessState.update { UpdateAdminAccessState.Progress }
            viewModelScope.launch {
                val id = state.admin.id
                updateAdminAccessUseCase.updateAdminAccess(id)
                    .onSuccess {
                        val newAdmin = state.admin.copy(accessMode = state.admin.accessMode + 1)
                        _adminDetailsState.update { ReadAdminDetailsState.Success(newAdmin) }
                        _adminAccessState.update { UpdateAdminAccessState.Success }
                    }
                    .onFailure {
                        _adminAccessState.update { UpdateAdminAccessState.Error }
                    }
            }
        }
    }

    fun deleteAdmin() {
        val state = adminDetailsState.value
        if (state is ReadAdminDetailsState.Success) {
            if (adminDeletingState.value == DeleteAdminState.Progress) return

            _adminDeletingState.update { DeleteAdminState.Progress }
            viewModelScope.launch {
                deleteAdminUseCase.deleteAdminById(state.admin.id)
                    .onSuccess {
                        _adminDeletingState.update { DeleteAdminState.Success }
                    }
                    .onFailure {
                        _adminDeletingState.update { DeleteAdminState.Error }
                    }
            }
        }
    }

    sealed interface DeleteAdminState {
        data object Initial : DeleteAdminState
        data object Error : DeleteAdminState
        data object Progress : DeleteAdminState
        data object Success : DeleteAdminState
    }

    sealed interface UpdateAdminAccessState {
        data object Initial : UpdateAdminAccessState
        data object Error : UpdateAdminAccessState
        data object Progress : UpdateAdminAccessState
        data object Success : UpdateAdminAccessState
    }

    sealed interface ReadAdminDetailsState {
        data object Initial : ReadAdminDetailsState
        data object Error : ReadAdminDetailsState
        data object Progress : ReadAdminDetailsState
        data class Success(val admin: AdminDetailsModel) : ReadAdminDetailsState
    }
}

























