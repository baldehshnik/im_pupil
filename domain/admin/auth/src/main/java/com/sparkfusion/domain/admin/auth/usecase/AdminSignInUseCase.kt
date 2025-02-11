package com.sparkfusion.domain.admin.auth.usecase

import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataPort.admin.portAuth.repository.IAdminAuthRepository
import com.sparkfusion.domain.admin.auth.mapper.JwtAuthenticationResponseEntityMapper
import com.sparkfusion.domain.admin.auth.mapper.SignInModelMapper
import com.sparkfusion.domain.admin.port.portauth.IAdminSignInUseCase
import com.sparkfusion.domain.admin.port.portauth.JwtAuthenticationModel
import com.sparkfusion.domain.admin.port.portauth.SignInModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
internal class AdminSignInUseCase @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val adminAuthRepository: IAdminAuthRepository,
    private val signInModelMapper: SignInModelMapper,
    private val jwtAuthenticationResponseEntityMapper: JwtAuthenticationResponseEntityMapper
) : IAdminSignInUseCase {

    override suspend fun signIn(singInModel: SignInModel): Answer<JwtAuthenticationModel> =
        withContext(ioDispatcher) {
            adminAuthRepository.signIn(signInModelMapper.map(singInModel))
                .suspendMap { jwtAuthenticationResponseEntityMapper.map(it) }
        }
}

















