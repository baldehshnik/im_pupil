package com.sparkfusion.domain.pupil.port.portauth.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.domain.pupil.port.portauth.model.JwtAuthenticationResponseModel
import com.sparkfusion.domain.pupil.port.portauth.model.SignInModel

interface ILoginPupilUseCase {

    suspend fun signInPupil(signIn: SignInModel): Answer<JwtAuthenticationResponseModel>
}
