package com.sparkfusion.portdomainservices.pupil.portmagazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.GroupMemberModel

interface IReadGroupMembersUseCase {

    suspend fun readGroupMembers(): Answer<List<GroupMemberModel>>
}