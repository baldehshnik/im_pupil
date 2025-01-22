package com.sparkfusion.domain.admin.port.portpost

import com.sparkfusion.core.common.result.Answer
import java.io.File

interface IUpdateInstitutionEventUseCase {

    suspend fun updateInstitutionEvent(
        model: UpdateInstitutionEventModel,
        image: File?
    ): Answer<Unit>
}