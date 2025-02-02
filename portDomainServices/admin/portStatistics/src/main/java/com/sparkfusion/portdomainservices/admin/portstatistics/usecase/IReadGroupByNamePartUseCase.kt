package com.sparkfusion.portdomainservices.admin.portstatistics.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portstatistics.model.GroupModel

interface IReadGroupByNamePartUseCase {

    suspend fun readGroupByNamePart(
        namePart: String
    ): Answer<List<GroupModel>>
}