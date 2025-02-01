package com.sparkfusion.portdomainservices.admin.portmagazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portmagazine.model.ReadGroupMemberWithPassesModel

interface IReadPassesUseCase {

    suspend fun readPasses(
        groupId: Int,
        lessonId: Int
    ): Answer<List<ReadGroupMemberWithPassesModel>>
}