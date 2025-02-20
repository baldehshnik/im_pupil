package com.sparkfusion.portdomainservices.pupil.portmagazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.GroupMemberWithPassModel
import java.time.LocalDate

interface IReadPassOfGroupMemberUseCase {

    suspend fun readPassOfGroupMember(
        groupMemberId: Int,
        lessonId: Int,
        date: LocalDate
    ): Answer<GroupMemberWithPassModel>
}