package com.sparkfusion.portdomainservices.admin.portschedule.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portschedule.model.UpdateScheduleModel

interface IUpdateScheduleUseCase {

    suspend fun updateSchedule(schedule: UpdateScheduleModel): Answer<Unit>
}