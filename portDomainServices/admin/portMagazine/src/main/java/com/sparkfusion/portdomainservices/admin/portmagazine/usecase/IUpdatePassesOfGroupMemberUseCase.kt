package com.sparkfusion.portdomainservices.admin.portmagazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portmagazine.model.UpdatePassesStatusModel

interface IUpdatePassesOfGroupMemberUseCase {

    suspend fun updatePassesOfGroupMember(updatePassesStatusEntity: UpdatePassesStatusModel): Answer<Unit>
}