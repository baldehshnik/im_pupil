package com.sparkfusion.portdomainservices.admin.portabout

import com.sparkfusion.core.common.result.Answer

interface IReadAboutBlocksUseCase {

    suspend fun readAboutBlock(): Answer<List<AboutModel>>
}