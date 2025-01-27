package com.sparkfusion.dataport.common.portabout

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.commonentity.about.CommonReadAboutDataEntity

interface IAboutRepository {

    suspend fun readAboutBlocksOfInstitution(
        institutionId: Int
    ): Answer<List<ReadAboutEntity>>
}






























