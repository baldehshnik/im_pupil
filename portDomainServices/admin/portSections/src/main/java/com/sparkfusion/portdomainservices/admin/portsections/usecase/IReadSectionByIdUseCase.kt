package com.sparkfusion.portdomainservices.admin.portsections.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portsections.model.ReadSectionModel

interface IReadSectionByIdUseCase {

    suspend fun readSectionById(id: Int): Answer<ReadSectionModel>
}