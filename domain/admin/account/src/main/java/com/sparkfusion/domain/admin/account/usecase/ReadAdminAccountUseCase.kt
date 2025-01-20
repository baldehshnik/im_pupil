package com.sparkfusion.domain.admin.account.usecase

import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataPort.admin.portaccount.IAdminAccountRepository
import com.sparkfusion.domain.admin.account.mapper.AdminEntityMapper
import com.sparkfusion.domain.admin.port.portaccount.AdminAccountModel
import com.sparkfusion.domain.admin.port.portaccount.IReadAdminAccountUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class ReadAdminAccountUseCase @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val adminAccountRepository: IAdminAccountRepository,
    private val adminEntityMapper: AdminEntityMapper
): IReadAdminAccountUseCase {

    override suspend fun readAdminAccount(): Answer<AdminAccountModel> = withContext(ioDispatcher) {
        adminAccountRepository.readAdminAccount()
            .suspendMap { adminEntityMapper.map(it) }
    }
}























