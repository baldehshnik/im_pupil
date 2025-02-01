package com.sparkfusion.portdomainservices.admin.portmagazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portmagazine.model.ReadLessonModel

interface IReadTodayScheduleUseCase {

    suspend fun readTodaySchedule(
        groupId: Int
    ): Answer<List<ReadLessonModel>>
}