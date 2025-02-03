package com.sparkfusion.portdomainservices.admin.portsections.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portsections.model.CreateSectionModel
import java.io.File

interface ICreateSectionUseCase {

    suspend fun createSection(
        createSection: CreateSectionModel,
        image: File
    ): Answer<Unit>
}