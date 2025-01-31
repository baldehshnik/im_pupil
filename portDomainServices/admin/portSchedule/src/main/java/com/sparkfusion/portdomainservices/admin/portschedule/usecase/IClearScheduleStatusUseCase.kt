package com.sparkfusion.portdomainservices.admin.portschedule.usecase

import com.sparkfusion.core.common.result.Answer

interface IClearScheduleStatusUseCase {

    suspend fun clearScheduleStatus(scheduleId: Int): Answer<Unit>
}