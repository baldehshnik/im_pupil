package com.sparkfusion.domainadminservices.sections.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portsections.ISectionsRepository
import com.sparkfusion.domainadminservices.sections.mapper.CreateSectionModelMapper
import com.sparkfusion.portdomainservices.admin.portsections.model.CreateSectionModel
import com.sparkfusion.portdomainservices.admin.portsections.usecase.ICreateSectionUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import java.io.File
import javax.inject.Inject

@ViewModelScoped
internal class CreateSectionUseCase @Inject constructor(
    private val sectionsRepository: ISectionsRepository,
    private val createSectionModelMapper: CreateSectionModelMapper
): ICreateSectionUseCase {

    override suspend fun createSection(
        createSection: CreateSectionModel,
        image: File
    ): Answer<Unit> {
        return sectionsRepository.createSection(
            createSectionModelMapper.map(createSection),
            image
        )
    }
}


















