package com.sparkfusion.portdomainservices.admin.portschedule.usecase

import com.sparkfusion.core.common.result.Answer

interface IMakeScheduleCurrentUseCase {

    suspend fun makeScheduleAsACurrent(scheduleId: Int): Answer<Unit>
}