package com.sparkfusion.domain.pupil.auth.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portauth.IAuthRepository
import com.sparkfusion.domain.pupil.auth.mapper.JwtAuthenticationResponseEntityMapper
import com.sparkfusion.domain.pupil.auth.mapper.SignInModelMapper
import com.sparkfusion.domain.pupil.port.portauth.model.JwtAuthenticationResponseModel
import com.sparkfusion.domain.pupil.port.portauth.model.SignInModel
import com.sparkfusion.domain.pupil.port.portauth.usecase.ILoginPupilUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class LoginPupilUseCase @Inject constructor(
    private val repository: IAuthRepository,
    private val signInModelMapper: SignInModelMapper,
    private val jwtAuthenticationResponseEntityMapper: JwtAuthenticationResponseEntityMapper
): ILoginPupilUseCase {

    override suspend fun signInPupil(signIn: SignInModel): Answer<JwtAuthenticationResponseModel> {
        return repository.signInPupil(signInModelMapper.map(signIn))
            .suspendMap { jwtAuthenticationResponseEntityMapper.map(it) }
    }
}





















