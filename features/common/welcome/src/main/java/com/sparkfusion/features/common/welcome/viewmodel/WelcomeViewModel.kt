package com.sparkfusion.features.common.welcome.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.core.common.user_type.CurrentUserTypeHolder
import com.sparkfusion.core.common.user_type.UserType
import com.sparkfusion.domain.admin.port.portauth.ICheckAdminTokenUseCase
import com.sparkfusion.domain.pupil.port.portauth.usecase.ICheckAccessTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(
    private val checkAdminTokenUseCase: ICheckAdminTokenUseCase,
    private val checkPupilTokenUseCase: ICheckAccessTokenUseCase
) : ViewModel() {

    private val _checkState = MutableStateFlow<CheckState>(CheckState.Initial)
    val checkState: StateFlow<CheckState> = _checkState.asStateFlow()

    fun checkLoginData(accessToken: String?) {
        if (checkState.value == CheckState.Progress) return

        if (accessToken.isNullOrEmpty()) {
            _checkState.update { CheckState.Failure("") }
            return
        }

        _checkState.update { CheckState.Progress }
        viewModelScope.launch {
            val userType = CurrentUserTypeHolder.type
            if (userType == UserType.Admin) {
                checkAdminTokenUseCase.checkAccessToken()
                    .onSuccess {
                        _checkState.update { CheckState.Success }
                    }
                    .onFailure {
                        _checkState.update { CheckState.Failure("Network error") }
                    }
            } else {
                checkPupilTokenUseCase.checkAccessToken()
                    .onSuccess {
                        _checkState.update { CheckState.Success }
                    }
                    .onFailure {
                        _checkState.update { CheckState.Failure("Network error") }
                    }
            }
        }
    }

    fun clearCheckState() {
        _checkState.update { CheckState.Initial }
    }

    sealed interface CheckState {
        data object Initial : CheckState
        data class Failure(val message: String) : CheckState
        data object Success : CheckState
        data object Progress : CheckState
    }
}


























