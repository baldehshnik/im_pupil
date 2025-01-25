package com.sparkfusion.domain.admin.port.portconfirmation

import com.sparkfusion.core.common.result.Answer

interface IReadUnconfirmedAdminsUseCase {

    suspend fun readUnconfirmedAdmins(): Answer<List<AdminModel>>
}