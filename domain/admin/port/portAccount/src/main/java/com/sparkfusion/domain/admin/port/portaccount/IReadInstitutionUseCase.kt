package com.sparkfusion.domain.admin.port.portaccount

import com.sparkfusion.core.common.result.Answer

interface IReadInstitutionUseCase {

    suspend fun readInstitution(): Answer<InstitutionModel>
}












