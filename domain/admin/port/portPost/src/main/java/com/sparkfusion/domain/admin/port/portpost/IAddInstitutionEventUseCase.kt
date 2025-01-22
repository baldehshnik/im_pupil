package com.sparkfusion.domain.admin.port.portpost

import com.sparkfusion.core.common.result.Answer
import java.io.File

interface IAddInstitutionEventUseCase {

    suspend fun addInstitutionEvent(model: AddInstitutionEventModel, image: File): Answer<Unit>
}