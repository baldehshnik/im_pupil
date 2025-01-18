package com.sparkfusion.domain.admin.port.portauth

import com.sparkfusion.core.common.result.Answer

interface ICheckAdminTokenUseCase {

    suspend fun checkAccessToken(): Answer<Unit>
}












