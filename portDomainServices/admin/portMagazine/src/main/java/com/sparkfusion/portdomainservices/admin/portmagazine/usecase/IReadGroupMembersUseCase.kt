package com.sparkfusion.portdomainservices.admin.portmagazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portmagazine.model.ReadGroupMemberModel

interface IReadGroupMembersUseCase {

    suspend fun readGroupMembersForMagazine(groupId: Int): Answer<List<ReadGroupMemberModel>>
}