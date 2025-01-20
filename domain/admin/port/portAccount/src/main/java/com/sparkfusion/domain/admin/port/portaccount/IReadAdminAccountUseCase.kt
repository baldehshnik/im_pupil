package com.sparkfusion.domain.admin.port.portaccount

import com.sparkfusion.core.common.result.Answer

interface IReadAdminAccountUseCase {

    suspend fun readAdminAccount(): Answer<AdminAccountModel>
}



























