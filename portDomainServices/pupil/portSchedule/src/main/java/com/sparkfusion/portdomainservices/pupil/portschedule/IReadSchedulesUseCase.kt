package com.sparkfusion.portdomainservices.pupil.portschedule

import com.sparkfusion.core.common.result.Answer

interface IReadSchedulesUseCase {

    suspend fun readSchedules(): Answer<List<ScheduleModel>>
}