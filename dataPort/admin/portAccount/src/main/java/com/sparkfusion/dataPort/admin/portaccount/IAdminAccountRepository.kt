package com.sparkfusion.dataPort.admin.portaccount

import com.sparkfusion.core.common.result.Answer

interface IAdminAccountRepository {

    suspend fun readAdminAccount(): Answer<AdminEntity>

    suspend fun readAdminsOfInstitution(): Answer<List<InstitutionAdminEntity>>
}














