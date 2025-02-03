package com.sparkfusion.domainadminservices.sections.usecase

import com.sparkfusion.core.common.exception.ImPupilException
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataPort.admin.portinstitution.IAdminInstitutionRepository
import com.sparkfusion.dataport.admin.portsections.ISectionsRepository
import com.sparkfusion.domainadminservices.sections.mapper.ReadSectionEntityMapper
import com.sparkfusion.portdomainservices.admin.portsections.model.ReadSectionModel
import com.sparkfusion.portdomainservices.admin.portsections.usecase.IReadSectionsUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ReadSectionsUseCase @Inject constructor(
    private val sectionsRepository: ISectionsRepository,
    private val institutionRepository: IAdminInstitutionRepository,
    private val readSectionEntityMapper: ReadSectionEntityMapper
): IReadSectionsUseCase {

    override suspend fun readSections(): Answer<List<ReadSectionModel>> {
        var error: ImPupilException? = null
        val result = institutionRepository.readInstitutionOfAdmin()
            .onFailure { error = it }

        if (error != null) return Answer.Failure(error!!)
        return sectionsRepository.readSections(result.unwrap().id)
            .suspendMap { list ->
                list.map { readSectionEntityMapper.map(it) }
            }
    }
}


















