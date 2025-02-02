package com.sparkfusion.portdomainservices.admin.portstatistics.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portstatistics.model.ReadFullPassModel

interface IReadGroupMemberPassesPerSemesterUseCase {

    suspend fun readGroupMemberPassesPerSemester(
        groupMemberId: Int
    ): Answer<List<ReadFullPassModel>>
}