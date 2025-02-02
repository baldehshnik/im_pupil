package com.sparkfusion.portdomainservices.admin.portstatistics.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portstatistics.model.ReadFullPassWithGroupMemberModel
import java.time.LocalDate

interface IReadPassesOfGroupPerMonthUseCase {

    suspend fun readPassesOfGroupPerMonth(
        groupId: Int
    ): Answer<List<ReadFullPassWithGroupMemberModel>>
}