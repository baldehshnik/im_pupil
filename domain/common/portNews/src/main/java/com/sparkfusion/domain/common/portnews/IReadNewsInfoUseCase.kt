package com.sparkfusion.domain.common.portnews

import com.sparkfusion.core.common.result.Answer

interface IReadNewsInfoUseCase {

    suspend fun readNewsInfoById(id: Int): Answer<NewsInfoModel>
}












