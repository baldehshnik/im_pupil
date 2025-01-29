package com.sparkfusion.portdomainservices.admin.portexam.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.admin.portexam.model.GroupModel

interface IReadGroupByNamePartUseCase {

    suspend fun readGroupByNamePart(
        namePart: String
    ): Answer<List<GroupModel>>
}