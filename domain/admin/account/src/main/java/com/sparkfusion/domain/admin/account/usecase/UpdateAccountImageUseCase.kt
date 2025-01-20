package com.sparkfusion.domain.admin.account.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataPort.admin.portaccount.IAdminAccountRepository
import com.sparkfusion.domain.admin.account.mapper.AdminNewImageEntityMapper
import com.sparkfusion.domain.admin.port.portaccount.AdminNewImageModel
import com.sparkfusion.domain.admin.port.portaccount.IUpdateAccountImageUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import java.io.File
import javax.inject.Inject

@ViewModelScoped
class UpdateAccountImageUseCase @Inject constructor(
    private val adminAccountRepository: IAdminAccountRepository,
    private val adminNewImageEntityMapper: AdminNewImageEntityMapper
): IUpdateAccountImageUseCase {

    override suspend fun updateImage(file: File): Answer<AdminNewImageModel> {
        return adminAccountRepository.updateAccountImage(file)
            .suspendMap { adminNewImageEntityMapper.map(it) }
    }
}




















