package com.sparkfusion.domainadminservices.sections.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portsections.ISectionsRepository
import com.sparkfusion.domainadminservices.sections.mapper.ReadSectionEntityMapper
import com.sparkfusion.portdomainservices.admin.portsections.model.ReadSectionModel
import com.sparkfusion.portdomainservices.admin.portsections.usecase.IReadSectionByIdUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ReadSectionByIdUseCase @Inject constructor(
    private val sectionsRepository: ISectionsRepository,
    private val readSectionEntityMapper: ReadSectionEntityMapper
) : IReadSectionByIdUseCase {

    override suspend fun readSectionById(id: Int): Answer<ReadSectionModel> {
        return sectionsRepository.readSectionById(id)
            .suspendMap { readSectionEntityMapper.map(it) }
    }
}























