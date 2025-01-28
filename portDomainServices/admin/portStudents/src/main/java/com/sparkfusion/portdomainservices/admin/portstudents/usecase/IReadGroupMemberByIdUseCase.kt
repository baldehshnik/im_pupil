package com.sparkfusion.portdomainservices.admin.portstudents.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portstudents.model.ReadGroupMemberModel

interface IReadGroupMemberByIdUseCase {

    suspend fun readGroupMemberById(id: Int): Answer<ReadGroupMemberModel>
}