package com.sparkfusion.portdomainservices.admin.portstudents.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portstudents.model.ReadGroupMemberModel

interface IReadGroupMembersUseCase {

    suspend fun readGroupMembers(groupId: Int): Answer<List<ReadGroupMemberModel>>
}