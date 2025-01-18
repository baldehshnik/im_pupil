package com.sparkfusion.dataPort.admin.portinstitution

import com.sparkfusion.core.common.result.Answer

interface IAdminInstitutionRepository {

    suspend fun readInstitutionOfAdmin(): Answer<InstitutionEntity>
}





















