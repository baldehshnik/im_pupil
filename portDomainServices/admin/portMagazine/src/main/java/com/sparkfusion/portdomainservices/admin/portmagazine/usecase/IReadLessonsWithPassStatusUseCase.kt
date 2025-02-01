package com.sparkfusion.portdomainservices.admin.portmagazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portmagazine.model.ReadLessonWithPassStatusModel
import java.time.LocalDate

interface IReadLessonsWithPassStatusUseCase {

    suspend fun readLessonsWithPassStatus(
        groupMemberId: Int,
        date: LocalDate
    ): Answer<List<ReadLessonWithPassStatusModel>>
}