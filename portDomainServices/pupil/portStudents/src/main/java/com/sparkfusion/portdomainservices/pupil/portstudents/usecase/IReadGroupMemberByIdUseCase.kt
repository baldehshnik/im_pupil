package com.sparkfusion.portdomainservices.pupil.portstudents.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.pupil.portstudents.model.GroupMemberModel

interface IReadGroupMemberByIdUseCase {

    suspend fun readGroupMemberById(id: Int): Answer<GroupMemberModel>
}