package com.sparkfusion.portdomainservices.admin.portmagazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portmagazine.model.UpdatePassStatusModel

interface IUpdatePassOfGroupMemberUseCase {

    suspend fun updatePassOfGroupMember(updatePassStatusEntity: UpdatePassStatusModel): Answer<Unit>
}