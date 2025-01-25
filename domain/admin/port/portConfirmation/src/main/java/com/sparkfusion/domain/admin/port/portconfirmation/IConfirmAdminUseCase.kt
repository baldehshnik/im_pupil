package com.sparkfusion.domain.admin.port.portconfirmation

import com.sparkfusion.core.common.result.Answer

interface IConfirmAdminUseCase {

    suspend fun confirmAdmin(id: Int): Answer<Unit>
}