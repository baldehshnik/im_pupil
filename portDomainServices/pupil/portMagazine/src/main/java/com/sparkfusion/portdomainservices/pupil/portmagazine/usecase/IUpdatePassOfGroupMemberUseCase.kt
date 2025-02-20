package com.sparkfusion.portdomainservices.pupil.portmagazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.UpdatePassStatusModel

interface IUpdatePassOfGroupMemberUseCase {

    suspend fun updatePassOfGroupMember(
        updatePassStatus: UpdatePassStatusModel
    ): Answer<Unit>
}