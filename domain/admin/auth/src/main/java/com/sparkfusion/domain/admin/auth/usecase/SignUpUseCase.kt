package com.sparkfusion.domain.admin.auth.usecase

import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataPort.admin.portAuth.repository.IAdminAuthRepository
import com.sparkfusion.domain.admin.auth.mapper.SignUpModelMapper
import com.sparkfusion.domain.admin.port.portauth.ISignUpUseCase
import com.sparkfusion.domain.admin.port.portauth.SignUpModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
internal class SignUpUseCase @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val adminAuthRepository: IAdminAuthRepository,
    private val signUpModelMapper: SignUpModelMapper
): ISignUpUseCase {

    override suspend fun signUp(signUpModel: SignUpModel): Answer<Unit> = withContext(ioDispatcher) {
        adminAuthRepository.signUp(signUpModelMapper.map(signUpModel))
    }
}


















