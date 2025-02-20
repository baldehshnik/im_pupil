package com.sparkfusion.portdomainservices.pupil.portmagazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.LessonWithPassStatusModel
import java.time.LocalDate

interface IReadScheduleWithPassesUseCase {

    suspend fun readScheduleWithPasses(
        groupMemberId: Int, date: LocalDate
    ): Answer<List<LessonWithPassStatusModel>>
}