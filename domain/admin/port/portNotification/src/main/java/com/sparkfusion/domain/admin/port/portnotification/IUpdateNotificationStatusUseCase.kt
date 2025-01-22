package com.sparkfusion.domain.admin.port.portnotification

import com.sparkfusion.core.common.result.Answer

interface IUpdateNotificationStatusUseCase {

    suspend fun updateStatus(id: Int): Answer<Unit>
}