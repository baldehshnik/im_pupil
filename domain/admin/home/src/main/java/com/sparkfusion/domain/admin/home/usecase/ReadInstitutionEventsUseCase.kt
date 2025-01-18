package com.sparkfusion.domain.admin.home.usecase

import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataPort.admin.portinstitution.IAdminInstitutionRepository
import com.sparkfusion.dataPort.common.portinstitutionevent.IInstitutionEventRepository
import com.sparkfusion.domain.admin.home.mapper.InstitutionEventEntityMapper
import com.sparkfusion.domain.admin.port.porthome.IReadInstitutionEventsUseCase
import com.sparkfusion.domain.admin.port.porthome.InstitutionEventModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class ReadInstitutionEventsUseCase @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val institutionRepository: IAdminInstitutionRepository,
    private val institutionEventRepository: IInstitutionEventRepository,
    private val institutionEventEntityMapper: InstitutionEventEntityMapper
) : IReadInstitutionEventsUseCase {

    override suspend fun readInstitutionEvents(): Answer<List<InstitutionEventModel>> =
        withContext(ioDispatcher) {
            val institutionAnswer = institutionRepository.readInstitutionOfAdmin()
                .onFailure { return@withContext Answer.Failure(it) }

            val institution = institutionAnswer.unwrap()
            institutionEventRepository.readInstitutionEvents(institution.id)
                .suspendMap {
                    it.map { model -> institutionEventEntityMapper.map(model) }
                }
        }
}

















