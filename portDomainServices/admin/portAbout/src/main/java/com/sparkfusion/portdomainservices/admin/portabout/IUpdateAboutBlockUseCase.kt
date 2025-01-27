package com.sparkfusion.portdomainservices.admin.portabout

import com.sparkfusion.core.common.result.Answer

interface IUpdateAboutBlockUseCase {

    suspend fun updateBlock(
        blocks: List<UpdateAboutModel>
    ): Answer<Unit>
}























