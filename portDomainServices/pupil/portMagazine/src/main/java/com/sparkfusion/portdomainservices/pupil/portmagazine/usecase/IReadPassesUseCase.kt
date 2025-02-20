package com.sparkfusion.portdomainservices.pupil.portmagazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.GroupMemberWithPassModel

interface IReadPassesUseCase {

    suspend fun readPasses(
        lessonId: Int
    ): Answer<List<GroupMemberWithPassModel>>
}