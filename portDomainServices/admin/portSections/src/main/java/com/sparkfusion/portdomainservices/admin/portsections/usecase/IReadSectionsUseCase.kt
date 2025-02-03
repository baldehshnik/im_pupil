package com.sparkfusion.portdomainservices.admin.portsections.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portsections.model.ReadSectionModel

interface IReadSectionsUseCase {

    suspend fun readSections(): Answer<List<ReadSectionModel>>
}