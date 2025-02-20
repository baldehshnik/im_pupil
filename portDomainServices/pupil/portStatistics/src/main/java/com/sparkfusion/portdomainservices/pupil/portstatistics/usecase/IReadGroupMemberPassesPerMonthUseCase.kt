package com.sparkfusion.portdomainservices.pupil.portstatistics.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.pupil.portstatistics.model.PassModel

interface IReadGroupMemberPassesPerMonthUseCase {

    suspend fun readGroupMemberPassesPerMonth(
        groupMemberId: Int
    ): Answer<List<PassModel>>
}