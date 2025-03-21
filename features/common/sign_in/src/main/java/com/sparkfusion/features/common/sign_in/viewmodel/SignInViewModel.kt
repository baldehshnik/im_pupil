package com.sparkfusion.features.common.sign_in.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.user_type.CurrentUserTypeHolder
import com.sparkfusion.core.common.user_type.UserType
import com.sparkfusion.domain.admin.port.portauth.IAdminSignInUseCase
import com.sparkfusion.domain.admin.port.portauth.SignInModel
import com.sparkfusion.domain.pupil.port.portauth.usecase.ILoginPupilUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.sparkfusion.domain.pupil.port.portauth.model.SignInModel as PupilSignInModel

@HiltViewModel
class SignInViewModel @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val adminSignInUseCase: IAdminSignInUseCase,
    private val pupilSignInUseCase: ILoginPupilUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    private val _signInState = MutableStateFlow<SignInState>(SignInState.Initial)
    val signInState: StateFlow<SignInState> = _signInState.asStateFlow()

    private val _signInDataState = MutableStateFlow<SignInDataState>(SignInDataState.Initial)
    val signInDataState: StateFlow<SignInDataState> = _signInDataState.asStateFlow()

    fun signIn() {
        if (signInState.value == SignInState.Progress) return

        if (!isEmailValid(state.value.email)) {
            _signInDataState.update { SignInDataState.Failure("Email is invalid") }
            return
        }

        if (state.value.password.length < 6) {
            _signInDataState.update { SignInDataState.Failure("Password too short") }
            return
        }

        _signInState.update { SignInState.Progress }
        viewModelScope.launch(ioDispatcher) {
            when (CurrentUserTypeHolder.type) {
                UserType.Admin -> {
                    val model = SignInModel(state.value.email, state.value.password)
                    adminSignInUseCase.signIn(model)
                        .onSuccess { m ->
                            _signInState.update {
                                SignInState.Success(
                                    m.accessToken,
                                    m.refreshToken
                                )
                            }
                        }
                        .onFailure {
                            _signInState.update { SignInState.Error }
                        }
                }

                UserType.Pupil -> {
                    val model = PupilSignInModel(state.value.email, state.value.password)
                    pupilSignInUseCase.signInPupil(model)
                        .onSuccess { m ->
                            _signInState.update {
                                SignInState.Success(
                                    m.accessToken,
                                    m.refreshToken
                                )
                            }
                        }
                        .onFailure {
                            _signInState.update { SignInState.Error }
                        }
                }
            }
        }
    }

    fun updateEmail(value: String) {
        _state.update { it.copy(email = value) }
    }

    fun updatePassword(value: String) {
        _state.update { it.copy(password = value) }
    }

    fun updatePasswordVisibility(value: Boolean) {
        _state.update { it.copy(showPassword = value) }
    }

    fun updateSaveLoginState(value: Boolean) {
        _state.update { it.copy(saveLogin = value) }
    }

    fun clearSignInDataState() {
        _signInDataState.update { SignInDataState.Initial }
    }

    fun clearSignInState() {
        _signInState.update { SignInState.Initial }
    }

    private fun isEmailValid(email: String): Boolean {
        val regex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
        return regex.matches(email)
    }

    data class State(
        val email: String = "",
        val password: String = "",
        val showPassword: Boolean = false,
        val saveLogin: Boolean = true
    )

    sealed interface SignInDataState {
        data object Initial : SignInDataState
        data class Failure(val message: String) : SignInDataState
    }

    sealed interface SignInState {
        data object Initial : SignInState
        data class Success(val accessToken: String, val refreshToken: String) : SignInState
        data object Error : SignInState
        data object Progress : SignInState
    }
}

















