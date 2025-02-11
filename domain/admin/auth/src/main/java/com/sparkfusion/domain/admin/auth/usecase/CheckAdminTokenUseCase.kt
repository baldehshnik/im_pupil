package com.sparkfusion.domain.admin.auth.usecase

import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataPort.admin.portAuth.repository.IAdminAuthRepository
import com.sparkfusion.domain.admin.port.portauth.ICheckAdminTokenUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
internal class CheckAdminTokenUseCase @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val adminAuthRepository: IAdminAuthRepository
): ICheckAdminTokenUseCase {

    override suspend fun checkAccessToken(): Answer<Unit> = withContext(ioDispatcher) {
        adminAuthRepository.checkToken()
    }
}



















