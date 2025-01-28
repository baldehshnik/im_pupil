package com.sparkfusion.portdomainservices.admin.portstudents.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portstudents.model.CreateGroupModel

interface ICreateGroupUseCase {

    suspend fun createGroup(createInstitutionGroupDto: CreateGroupModel): Answer<Unit>
}