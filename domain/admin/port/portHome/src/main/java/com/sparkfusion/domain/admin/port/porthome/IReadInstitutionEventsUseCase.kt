package com.sparkfusion.domain.admin.port.porthome

import com.sparkfusion.core.common.result.Answer

interface IReadInstitutionEventsUseCase {

    suspend fun readInstitutionEvents(): Answer<List<InstitutionEventModel>>
}

















