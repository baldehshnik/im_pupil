package com.sparkfusion.portdomainservices.admin.portschedule.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portschedule.model.ReadLessonModel

interface IReadLessonsUseCase {

    suspend fun readLessonsByScheduleId(scheduleId: Int): Answer<List<ReadLessonModel>>
}