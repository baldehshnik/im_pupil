package com.sparkfusion.domain.admin.home.usecase

import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataPort.admin.portaccount.IAdminAccountRepository
import com.sparkfusion.domain.admin.home.mapper.AdminAccountEntityMapper
import com.sparkfusion.domain.admin.port.porthome.AccountModel
import com.sparkfusion.domain.admin.port.porthome.IReadAccountUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
internal class ReadAccountUseCase @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val repository: IAdminAccountRepository,
    private val adminAccountEntityMapper: AdminAccountEntityMapper
) : IReadAccountUseCase {

    override suspend fun readAccount(): Answer<AccountModel> = withContext(ioDispatcher) {
        repository.readAdminAccount()
            .suspendMap { adminAccountEntityMapper.map(it) }
    }
}
























