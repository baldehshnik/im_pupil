package com.sparkfusion.portdomainservices.admin.portstudents.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portstudents.model.GroupModel

interface IReadGroupsUseCase {

    suspend fun readGroupBySpeciality(specialityId: Int): Answer<List<GroupModel>>
}