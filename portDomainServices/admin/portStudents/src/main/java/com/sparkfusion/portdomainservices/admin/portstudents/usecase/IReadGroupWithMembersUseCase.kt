package com.sparkfusion.portdomainservices.admin.portstudents.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portstudents.model.ReadGroupWithMembersModel

interface IReadGroupWithMembersUseCase {

    suspend fun readGroupWithMembers(id: Int): Answer<ReadGroupWithMembersModel>
}