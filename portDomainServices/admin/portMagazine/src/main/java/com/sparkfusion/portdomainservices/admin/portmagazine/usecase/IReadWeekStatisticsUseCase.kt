package com.sparkfusion.portdomainservices.admin.portmagazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portmagazine.model.ReadWeekDayPassModel

interface IReadWeekStatisticsUseCase {

    suspend fun readWeekStatistics(
        groupMemberId: Int
    ): Answer<List<ReadWeekDayPassModel>>
}