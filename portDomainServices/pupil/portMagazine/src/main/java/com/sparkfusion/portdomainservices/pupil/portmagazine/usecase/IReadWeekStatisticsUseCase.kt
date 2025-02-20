package com.sparkfusion.portdomainservices.pupil.portmagazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.WeekDayPassModel

interface IReadWeekStatisticsUseCase {

    suspend fun readWeekStatistics(
        groupMemberId: Int
    ): Answer<List<WeekDayPassModel>>
}