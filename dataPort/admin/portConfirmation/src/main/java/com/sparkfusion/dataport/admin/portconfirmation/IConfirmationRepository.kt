package com.sparkfusion.dataport.admin.portconfirmation

import com.sparkfusion.core.common.result.Answer

interface IConfirmationRepository {

    suspend fun readNotConfirmedAdmins(): Answer<List<AdminEntity>>

    suspend fun confirmAdmin(adminId: Int): Answer<Unit>

    suspend fun readNotConfirmedPupils(institutionId: Int): Answer<List<PupilEntity>>

    suspend fun confirmPupil(id: Int): Answer<Unit>
}



























