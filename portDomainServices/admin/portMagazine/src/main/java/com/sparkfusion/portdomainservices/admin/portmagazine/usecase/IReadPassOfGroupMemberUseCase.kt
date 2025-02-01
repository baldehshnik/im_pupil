package com.sparkfusion.portdomainservices.admin.portmagazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portmagazine.model.ReadGroupMemberWithPassesModel

interface IReadPassOfGroupMemberUseCase {

    suspend fun readPassOfGroupMember(
        groupMemberId: Int,
        lessonId: Int
    ): Answer<ReadGroupMemberWithPassesModel>
}