package com.sparkfusion.features.admin.account.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.domain.admin.port.portaccount.AdminAccountModel
import com.sparkfusion.domain.admin.port.portaccount.IReadAdminAccountUseCase
import com.sparkfusion.domain.admin.port.portaccount.IReadAllAdminsOfInstitutionUseCase
import com.sparkfusion.domain.admin.port.portaccount.IReadInstitutionUseCase
import com.sparkfusion.domain.admin.port.portaccount.InstitutionAdminModel
import com.sparkfusion.domain.admin.port.portaccount.InstitutionModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val readInstitutionUseCase: IReadInstitutionUseCase,
    private val readAdminAccountUseCase: IReadAdminAccountUseCase,
    private val readAllAdminsOfInstitutionUseCase: IReadAllAdminsOfInstitutionUseCase
) : ViewModel() {

    private val _institutionState = MutableStateFlow<InstitutionState>(InstitutionState.Initial)
    val institutionState: StateFlow<InstitutionState> = _institutionState.asStateFlow()

    private val _adminsState = MutableStateFlow<AdminsState>(AdminsState.Initial)
    val adminsState: StateFlow<AdminsState> = _adminsState.asStateFlow()

    private val _accountState = MutableStateFlow<AccountState>(AccountState.Initial)
    val accountState: StateFlow<AccountState> = _accountState.asStateFlow()

    fun readInstitutionInfo() {
        _institutionState.update { InstitutionState.Progress }
        viewModelScope.launch(ioDispatcher) {
            readInstitutionUseCase.readInstitution()
                .onSuccess { model ->
                    Log.d("TAGTAG", "MODEL - $model")
                    _institutionState.update { InstitutionState.Success(model) }
                }
                .onFailure {
                    Log.d("TAGTAG", "ERROR INSTITUTION - ${it.message}")
                    _institutionState.update { InstitutionState.Error }
                }
        }
    }

    fun readAccountInfo() {
        _accountState.update { AccountState.Progress }
        viewModelScope.launch(ioDispatcher) {
            readAdminAccountUseCase.readAdminAccount()
                .onSuccess { model ->
                    Log.d("TAGTAG", "MODEL - $model")
                    _accountState.update { AccountState.Success(model) }
                }
                .onFailure {
                    Log.d("TAGTAG", "ERROR ACCOUNT - ${it.message}")
                    _accountState.update { AccountState.Error }
                }
        }
    }

    fun readAdmins() {
        _adminsState.update { AdminsState.Progress }
        viewModelScope.launch(ioDispatcher) {
            readAllAdminsOfInstitutionUseCase.readAdminsOfInstitution()
                .onSuccess { list ->
                    Log.d("TAGTAG", "MODEL - $list")
                    _adminsState.update { AdminsState.Success(list) }
                }
                .onFailure {
                    Log.d("TAGTAG", "ERROR ADMINS - ${it.message}")
                    _adminsState.update { AdminsState.Error }
                }
        }
    }

    sealed interface AdminsState {
        data object Initial : AdminsState
        data object Error : AdminsState
        data object Progress : AdminsState
        data class Success(val data: List<InstitutionAdminModel>): AdminsState
    }

    sealed interface AccountState {
        data object Initial : AccountState
        data object Error : AccountState
        data object Progress : AccountState
        data class Success(val data: AdminAccountModel): AccountState
    }

    sealed interface InstitutionState {
        data object Initial : InstitutionState
        data object Error : InstitutionState
        data object Progress : InstitutionState
        data class Success(val data: InstitutionModel): InstitutionState
    }
}
































