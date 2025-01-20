package com.sparkfusion.dataPort.admin.portaccount

import com.sparkfusion.core.common.result.Answer
import java.io.File

interface IAdminAccountRepository {

    suspend fun readAdminAccount(): Answer<AdminEntity>

    suspend fun readAdminsOfInstitution(): Answer<List<InstitutionAdminEntity>>

    suspend fun updateAccountImage(image: File): Answer<AdminNewImageEntity>
}














