package com.sparkfusion.portdomainservices.pupil.portschedule

import com.sparkfusion.core.common.result.Answer

interface IReadScheduleWithLessonsUseCase {

    suspend fun readScheduleWithLessons(id: Int): Answer<ScheduleWithLessonsModel>
}