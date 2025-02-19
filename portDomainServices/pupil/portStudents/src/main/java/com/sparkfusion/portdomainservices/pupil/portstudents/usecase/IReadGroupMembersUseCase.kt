package com.sparkfusion.portdomainservices.pupil.portstudents.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.pupil.portstudents.model.GroupMemberModel

interface IReadGroupMembersUseCase {

    suspend fun readGroupMembers(): Answer<List<GroupMemberModel>>
}