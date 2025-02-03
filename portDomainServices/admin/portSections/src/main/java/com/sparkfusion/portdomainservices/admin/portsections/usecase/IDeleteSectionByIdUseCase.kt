package com.sparkfusion.portdomainservices.admin.portsections.usecase

import com.sparkfusion.core.common.result.Answer

interface IDeleteSectionByIdUseCase {

    suspend fun deleteSectionById(id: Int): Answer<Unit>
}