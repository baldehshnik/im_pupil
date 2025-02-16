package com.sparkfusion.data.common.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.commonentity.CommonNewsGuideDataEntity
import com.sparkfusion.dataPort.common.portnews.NewsGuideEntity
import javax.inject.Inject

internal class GuideDataEntityMapper @Inject constructor(
): Mapper<CommonNewsGuideDataEntity, NewsGuideEntity> {

    override suspend fun map(input: CommonNewsGuideDataEntity): NewsGuideEntity = with(input) {
        NewsGuideEntity(id, title, description, image)
    }
}





















