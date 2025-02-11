package com.sparkfusion.domain.pupil.auth.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portauth.IAuthRepository
import com.sparkfusion.domain.pupil.port.portauth.usecase.ICheckAccessTokenUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class CheckAccessTokenUseCase @Inject constructor(
    private val repository: IAuthRepository
): ICheckAccessTokenUseCase {

    override suspend fun checkAccessToken(): Answer<Unit> {
        return repository.checkAccessToken()
    }
}





















