package com.sparkfusion.portdomainservices.pupil.portmagazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.LessonModel

interface IReadTodayScheduleUseCase {

    suspend fun readTodaySchedule(): Answer<List<LessonModel>>
}