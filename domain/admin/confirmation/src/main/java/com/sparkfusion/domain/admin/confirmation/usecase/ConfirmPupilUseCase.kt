package com.sparkfusion.domain.admin.confirmation.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portconfirmation.IConfirmationRepository
import com.sparkfusion.domain.admin.port.portconfirmation.IConfirmPupilUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ConfirmPupilUseCase @Inject constructor(
    private val confirmationRepository: IConfirmationRepository
): IConfirmPupilUseCase {

    override suspend fun confirmPupil(id: Int): Answer<Unit> {
        return confirmationRepository.confirmPupil(id)
    }
}