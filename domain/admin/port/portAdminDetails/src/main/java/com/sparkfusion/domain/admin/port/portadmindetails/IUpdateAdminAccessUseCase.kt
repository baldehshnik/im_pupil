package com.sparkfusion.domain.admin.port.portadmindetails

import com.sparkfusion.core.common.result.Answer

interface IUpdateAdminAccessUseCase {

    suspend fun updateAdminAccess(id: Int): Answer<Unit>
}