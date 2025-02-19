package com.sparkfusion.domainpupilservices.section.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portsection.ISectionRepository
import com.sparkfusion.domainpupilservices.section.mapper.SectionEntityMapper
import com.sparkfusion.portdomainservices.pupil.portsection.IReadSectionsUseCase
import com.sparkfusion.portdomainservices.pupil.portsection.SectionModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ReadSectionsUseCase @Inject constructor(
    private val sectionRepository: ISectionRepository,
    private val sectionEntityMapper: SectionEntityMapper
) : IReadSectionsUseCase {

    override suspend fun readSections(): Answer<List<SectionModel>> {
        return sectionRepository.readSections()
            .suspendMap { list ->
                list.map {
                    sectionEntityMapper.map(it)
                }
            }
    }
}



















