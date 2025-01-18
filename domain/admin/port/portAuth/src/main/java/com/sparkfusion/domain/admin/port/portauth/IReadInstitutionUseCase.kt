package com.sparkfusion.domain.admin.port.portauth

import com.sparkfusion.core.common.result.Answer

interface IReadInstitutionUseCase {

    suspend fun readInstitutionByNamePart(namePart: String): Answer<List<InstitutionModel>>
}








