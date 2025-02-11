package com.sparkfusion.domain.common.news.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.commonentity.CommonNewsGuideDataEntity
import com.sparkfusion.domain.common.portnews.NewsGuideModel
import javax.inject.Inject

internal class NewsGuideEntityMapper @Inject constructor(
): Mapper<CommonNewsGuideDataEntity, NewsGuideModel> {

    override suspend fun map(input: CommonNewsGuideDataEntity): NewsGuideModel = with(input) {
        NewsGuideModel(id, title, description, image)
    }
}