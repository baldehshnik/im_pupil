package com.sparkfusion.domain.admin.confirmation.usecase

import com.sparkfusion.core.common.exception.ImPupilException
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataPort.admin.portinstitution.IAdminInstitutionRepository
import com.sparkfusion.dataport.admin.portconfirmation.IConfirmationRepository
import com.sparkfusion.domain.admin.confirmation.mapper.PupilEntityMapper
import com.sparkfusion.domain.admin.port.portconfirmation.IReadUnconfirmedPupilsUseCase
import com.sparkfusion.domain.admin.port.portconfirmation.PupilModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ReadUnconfirmedPupilsUseCase @Inject constructor(
    private val pupilEntityMapper: PupilEntityMapper,
    private val confirmationRepository: IConfirmationRepository,
    private val institutionRepository: IAdminInstitutionRepository
) : IReadUnconfirmedPupilsUseCase {

    override suspend fun readUnconfirmedPupils(): Answer<List<PupilModel>> {
        var error: ImPupilException? = null
        val result = institutionRepository.readInstitutionOfAdmin()
            .onFailure {
                error = it
            }

        if (error != null) return Answer.Failure(error!!)
        return confirmationRepository.readNotConfirmedPupils(result.unwrap().id)
            .suspendMap { it.map { model -> pupilEntityMapper.map(model) } }
    }
}
















