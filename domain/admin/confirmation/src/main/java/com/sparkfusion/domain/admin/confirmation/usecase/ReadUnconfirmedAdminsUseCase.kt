package com.sparkfusion.domain.admin.confirmation.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portconfirmation.IConfirmationRepository
import com.sparkfusion.domain.admin.confirmation.mapper.AdminEntityMapper
import com.sparkfusion.domain.admin.port.portconfirmation.AdminModel
import com.sparkfusion.domain.admin.port.portconfirmation.IReadUnconfirmedAdminsUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ReadUnconfirmedAdminsUseCase @Inject constructor(
    private val adminEntityMapper: AdminEntityMapper,
    private val confirmationRepository: IConfirmationRepository
): IReadUnconfirmedAdminsUseCase {

    override suspend fun readUnconfirmedAdmins(): Answer<List<AdminModel>> {
        return confirmationRepository.readNotConfirmedAdmins()
            .suspendMap { it.map { model -> adminEntityMapper.map(model) } }
    }
}