package com.sparkfusion.features.pupil.sign_up.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sparkfusion.domain.pupil.port.portauth.model.AddPupilModel
import com.sparkfusion.domain.pupil.port.portauth.model.EducationalInstitutionModel
import com.sparkfusion.domain.pupil.port.portauth.usecase.IReadInstitutionByNamePartUseCase
import com.sparkfusion.domain.pupil.port.portauth.usecase.IRegisterPupilUseCase
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: Lazy<IRegisterPupilUseCase>,
    private val readInstitutionUseCase: Lazy<IReadInstitutionByNamePartUseCase>
): ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    private val _institutionState = MutableStateFlow<List<EducationalInstitutionModel>>(emptyList())
    val institutionState: StateFlow<List<EducationalInstitutionModel>> = _institutionState.asStateFlow()

    private val _signUpState = MutableStateFlow<SignUpState>(SignUpState.Initial)
    val singUpState: StateFlow<SignUpState> = _signUpState.asStateFlow()

    private val _signUpDataState = MutableStateFlow<SignUpDataState>(SignUpDataState.Initial)
    val singUpDataState: StateFlow<SignUpDataState> = _signUpDataState.asStateFlow()

    fun readInstitutionByNamePart(namePart: String) {
        viewModelScope.launch {
            readInstitutionUseCase.get().readInstitutionByNamePart(namePart)
                .onSuccess { list ->
                    Log.i("TAGTAG", list.joinToString())
                    _institutionState.update { list }
                }
                .onFailure {
                    Log.i("TAGTAG", it.message.toString())
                }
        }
    }

    fun setInstitution(id: Int) {
        _state.update { it.copy(educationalInstitutionId = id) }
    }

    fun clearSignUpState() {
        _signUpState.update { SignUpState.Initial }
    }

    fun clearSignUpDataState() {
        _signUpDataState.update { SignUpDataState.Initial }
    }

    fun signUp() {
        if (singUpState.value == SignUpState.Progress) return

        if (state.value.code == "") {
            _signUpDataState.update { SignUpDataState.Failure("Code empty") }
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
        if (state.value.educationalInstitutionId == -1) {
            _signUpDataState.update { SignUpDataState.Failure("institution was not selected") }
            return
        }

        _signUpState.update { SignUpState.Progress }
        viewModelScope.launch {
            val model = AddPupilModel(
                state.value.code,
                state.value.email,
                state.value.password,
                state.value.educationalInstitutionId
            )
            signUpUseCase.get().signUpPupil(model)
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

    fun updateCode(value: String) {
        _state.update { it.copy(code = value) }
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
        val code: String = "",
        val email: String = "",
        val password: String = "",
        val educationalInstitutionId: Int = -1
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

























