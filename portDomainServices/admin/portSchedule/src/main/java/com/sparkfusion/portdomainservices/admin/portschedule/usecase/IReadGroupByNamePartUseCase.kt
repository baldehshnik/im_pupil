package com.sparkfusion.portdomainservices.admin.portschedule.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portschedule.model.GroupModel

interface IReadGroupByNamePartUseCase {

    suspend fun readGroupByNamePart(
        namePart: String
    ): Answer<List<GroupModel>>
}