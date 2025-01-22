package com.sparkfusion.domain.admin.port.portpost

import com.sparkfusion.core.common.result.Answer

interface IReadInstitutionEventUseCase {

    suspend fun readInstitutionEvent(id: Int): Answer<InstitutionEventModel>
}