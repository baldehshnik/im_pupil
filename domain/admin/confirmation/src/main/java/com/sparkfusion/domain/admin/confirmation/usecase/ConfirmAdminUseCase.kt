package com.sparkfusion.domain.admin.confirmation.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portconfirmation.IConfirmationRepository
import com.sparkfusion.domain.admin.port.portconfirmation.IConfirmAdminUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ConfirmAdminUseCase @Inject constructor(
    private val confirmationRepository: IConfirmationRepository
): IConfirmAdminUseCase {

    override suspend fun confirmAdmin(id: Int): Answer<Unit> {
        return confirmationRepository.confirmAdmin(id)
    }
}