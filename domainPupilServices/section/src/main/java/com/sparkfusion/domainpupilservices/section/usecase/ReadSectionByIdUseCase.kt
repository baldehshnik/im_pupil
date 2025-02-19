package com.sparkfusion.domainpupilservices.section.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portsection.ISectionRepository
import com.sparkfusion.domainpupilservices.section.mapper.SectionEntityMapper
import com.sparkfusion.portdomainservices.pupil.portsection.IReadSectionByIdUseCase
import com.sparkfusion.portdomainservices.pupil.portsection.SectionModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ReadSectionByIdUseCase @Inject constructor(
    private val sectionRepository: ISectionRepository,
    private val sectionEntityMapper: SectionEntityMapper
) : IReadSectionByIdUseCase {

    override suspend fun readSectionById(id: Int): Answer<SectionModel> {
        return sectionRepository.readSectionById(id)
            .suspendMap { sectionEntityMapper.map(it) }
    }
}

















