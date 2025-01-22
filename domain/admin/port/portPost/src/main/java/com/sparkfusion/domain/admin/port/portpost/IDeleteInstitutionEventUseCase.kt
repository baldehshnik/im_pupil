package com.sparkfusion.domain.admin.port.portpost

import com.sparkfusion.core.common.result.Answer

interface IDeleteInstitutionEventUseCase {

    suspend fun deleteInstitutionEventById(id: Int): Answer<Unit>
}




















