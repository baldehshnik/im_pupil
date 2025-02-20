package com.sparkfusion.portdomainservices.pupil.portstatistics.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.pupil.portstatistics.model.GroupMemberModel

interface IReadGroupMembersForStatisticsUseCase {

    suspend fun readGroupMembersForStatistics(): Answer<List<GroupMemberModel>>
}