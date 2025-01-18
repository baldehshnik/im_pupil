package com.sparkfusion.features.admin.sign_up.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.domain.admin.port.portauth.IReadInstitutionUseCase
import com.sparkfusion.domain.admin.port.portauth.ISignUpUseCase
import com.sparkfusion.domain.admin.port.portauth.InstitutionModel
import com.sparkfusion.domain.admin.port.portauth.SignUpModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingUpViewModel @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val signUpUseCase: ISignUpUseCase,
    private val readInstitutionUseCase: IReadInstitutionUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    private val _institutionState = MutableStateFlow<List<InstitutionModel>>(emptyList())
    val institutionState: StateFlow<List<InstitutionModel>> = _institutionState.asStateFlow()

    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState.Initial)
    val singUpState: StateFlow<SignUpState> = _signUpState.asStateFlow()

    private val _signUpDataState = MutableStateFlow<SignUpDataState>(SignUpDataState.Initial)
    val singUpDataState: StateFlow<SignUpDataState> = _signUpDataState.asStateFlow()

    fun readInstitutionByNamePart(namePart: String) {
        viewModelScope.launch(ioDispatcher) {
            readInstitutionUseCase.readInstitutionByNamePart(namePart)
                .onSuccess { list ->
                    Log.i("TAGTAG", list.joinToString())
                    _institutionState.update { list }
                }
                .onFailure {
                    Log.i("TAGTAG", it.message.toString())
                }
        }
    }

    fun setInstitution(abbreviation: String) {
        _state.update { it.copy(educationalInstitutionAbbreviation = abbreviation) }
    }

    fun clearSignUpState() {
        _signUpState.update { SignUpState.Initial }
    }

    fun clearSignUpDataState() {
        _signUpDataState.update { SignUpDataState.Initial }
    }

    fun signUp() {
        if (singUpState.value == SignUpState.Progress) return

        if (state.value.firstname == "") {
            _signUpDataState.update { SignUpDataState.Failure("Firstname empty") }
            return
        }
        if (state.value.lastname == "") {
            _signUpDataState.update { SignUpDataState.Failure("Lastname empty") }
            return
        }
        if (state.value.patronymic == "") {
            _signUpDataState.update { SignUpDataState.Failure("Patronymic empty") }
            return
        }
        if (!isEmailValid(state.value.email)) {
            _signUpDataState.update { SignUpDataState.Failure("Email incorrect") }
            return
        }
        if (state.value.password.length < 6) {
            _signUpDataState.update { SignUpDataState.Failure("Password too short") }
            return
        }
        if (state.value.educationalInstitutionAbbreviation == "") {
            _signUpDataState.update { SignUpDataState.Failure("institution was not selected") }
            return
        }

        _signUpState.update { SignUpState.Progress }
        viewModelScope.launch(ioDispatcher) {
            val model = SignUpModel(
                state.value.firstname,
                state.value.lastname,
                state.value.patronymic,
                state.value.email,
                state.value.password,
                state.value.educationalInstitutionAbbreviation
            )
            signUpUseCase.signUp(model)
                .onSuccess {
                    Log.i("TAGTAG", "Success")
                    _signUpState.update { SignUpState.Success }
                }
                .onFailure {
                    Log.i("TAGTAG", "Failure")
                    _signUpState.update { SignUpState.Error }
                }
        }
    }

    fun updateFirstname(value: String) {
        _state.update { it.copy(firstname = value) }
    }

    fun updateLastname(value: String) {
        _state.update { it.copy(lastname = value) }
    }

    fun updatePatronymic(value: String) {
        _state.update { it.copy(patronymic = value) }
    }

    fun updateEmail(value: String) {
        _state.update { it.copy(email = value) }
    }

    fun updatePassword(value: String) {
        _state.update { it.copy(password = value) }
    }

    private fun isEmailValid(email: String): Boolean {
        val regex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
        return regex.matches(email)
    }

    data class State(
        val firstname: String = "",
        val lastname: String = "",
        val patronymic: String = "",
        val email: String = "",
        val password: String = "",
        val educationalInstitutionAbbreviation: String = ""
    )

    sealed interface SignUpDataState {
        data object Initial : SignUpDataState
        data class Failure(val value: String) : SignUpDataState
    }

    sealed interface SignUpState {
        data object Initial : SignUpState
        data object Progress : SignUpState
        data object Error : SignUpState
        data object Success : SignUpState
    }
}























