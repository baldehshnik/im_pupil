package com.sparkfusion.portdomainservices.admin.portstudents.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portstudents.model.UpdateGroupModel

interface IUpdateGroupUseCase {

    suspend fun updateGroup(updateInstitutionGroupDto: UpdateGroupModel): Answer<Unit>
}