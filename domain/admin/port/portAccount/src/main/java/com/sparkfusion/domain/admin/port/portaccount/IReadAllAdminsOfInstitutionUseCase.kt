package com.sparkfusion.domain.admin.port.portaccount

import com.sparkfusion.core.common.result.Answer

interface IReadAllAdminsOfInstitutionUseCase {

    suspend fun readAdminsOfInstitution(): Answer<List<InstitutionAdminModel>>
}