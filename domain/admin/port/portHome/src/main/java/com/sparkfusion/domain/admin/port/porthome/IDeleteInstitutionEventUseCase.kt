package com.sparkfusion.domain.admin.port.porthome

import com.sparkfusion.core.common.result.Answer

interface IDeleteInstitutionEventUseCase {

    suspend fun deleteById(id: Int): Answer<Unit>
}