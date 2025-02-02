package com.sparkfusion.portdomainservices.admin.portstatistics.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portstatistics.model.ReadGroupMemberModel

interface IReadGroupMembersForStatisticsUseCase {

    suspend fun readGroupMembersForStatistics(groupId: Int): Answer<List<ReadGroupMemberModel>>
}