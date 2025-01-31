package com.sparkfusion.portdomainservices.admin.portschedule.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portschedule.model.AddScheduleModel

interface ICreateScheduleUseCase {

    suspend fun createSchedule(schedule: AddScheduleModel): Answer<Unit>
}