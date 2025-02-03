package com.sparkfusion.portdomainservices.admin.portsections.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portsections.model.UpdateSectionModel
import java.io.File

interface IUpdateSectionUseCase {

    suspend fun updateSection(
        updateSection: UpdateSectionModel,
        image: File?
    ): Answer<Unit>
}