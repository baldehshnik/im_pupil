package com.sparkfusion.dataPort.common.portnews

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.commonentity.CommonNewsInfoDataEntity

interface INewsRepository {

    suspend fun loadNewsById(id: Int): Answer<CommonNewsInfoDataEntity>

    suspend fun loadNews(): Answer<List<NewsEntity>>
}













