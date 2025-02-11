package com.sparkfusion.domainadminservices.sections.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portsections.ISectionsRepository
import com.sparkfusion.domainadminservices.sections.mapper.UpdateSectionModelMapper
import com.sparkfusion.portdomainservices.admin.portsections.model.UpdateSectionModel
import com.sparkfusion.portdomainservices.admin.portsections.usecase.IUpdateSectionUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import java.io.File
import javax.inject.Inject

@ViewModelScoped
internal class UpdateSectionUseCase @Inject constructor(
    private val sectionsRepository: ISectionsRepository,
    private val updateSectionModelMapper: UpdateSectionModelMapper
): IUpdateSectionUseCase {

    override suspend fun updateSection(
        updateSection: UpdateSectionModel,
        image: File?
    ): Answer<Unit> {
        return sectionsRepository.updateSection(
            updateSectionModelMapper.map(updateSection),
            image
        )
    }
}




















