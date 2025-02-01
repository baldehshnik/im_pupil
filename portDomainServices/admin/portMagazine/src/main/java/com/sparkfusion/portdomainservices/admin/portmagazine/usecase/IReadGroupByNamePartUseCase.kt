package com.sparkfusion.portdomainservices.admin.portmagazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portmagazine.model.GroupModel

interface IReadGroupByNamePartUseCase {

    suspend fun readGroupByNamePart(
        namePart: String
    ): Answer<List<GroupModel>>
}