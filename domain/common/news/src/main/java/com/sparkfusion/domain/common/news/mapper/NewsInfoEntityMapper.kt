package com.sparkfusion.domain.common.news.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.commonentity.CommonNewsInfoDataEntity
import com.sparkfusion.domain.common.portnews.NewsInfoModel
import javax.inject.Inject

class NewsInfoEntityMapper @Inject constructor(
    private val newsGuideEntityMapper: NewsGuideEntityMapper
): Mapper<CommonNewsInfoDataEntity, NewsInfoModel> {

    override suspend fun map(input: CommonNewsInfoDataEntity): NewsInfoModel = with(input) {
        NewsInfoModel(id, title, imageUrl, description, guides.map {
            newsGuideEntityMapper.map(it)
        })
    }
}






















