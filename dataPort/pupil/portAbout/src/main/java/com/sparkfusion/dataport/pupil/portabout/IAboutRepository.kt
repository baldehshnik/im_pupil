package com.sparkfusion.dataport.pupil.portabout

import com.sparkfusion.core.common.result.Answer

interface IAboutRepository {

    suspend fun readAboutBlocks(): Answer<List<AboutEntity>>
}