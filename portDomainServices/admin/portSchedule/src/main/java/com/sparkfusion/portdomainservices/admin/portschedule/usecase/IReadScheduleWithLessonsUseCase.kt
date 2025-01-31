package com.sparkfusion.portdomainservices.admin.portschedule.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portschedule.model.ReadScheduleWithLessonsModel

interface IReadScheduleWithLessonsUseCase {

    suspend fun readScheduleWithLessons(id: Int): Answer<ReadScheduleWithLessonsModel>
}