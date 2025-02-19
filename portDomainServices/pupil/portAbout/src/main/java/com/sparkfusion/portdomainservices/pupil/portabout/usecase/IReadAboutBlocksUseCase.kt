package com.sparkfusion.portdomainservices.pupil.portabout.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.pupil.portabout.model.AboutModel

interface IReadAboutBlocksUseCase {

    suspend fun readAboutBlocks(): Answer<List<AboutModel>>
}