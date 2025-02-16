package com.sparkfusion.data.common.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.common.entity.NewsEntity
import com.sparkfusion.dataPort.common.portnews.NewsEntity as PortNewsEntity
import javax.inject.Inject

internal class NewsDataEntityMapper @Inject constructor(
): Mapper<NewsEntity, PortNewsEntity> {

    override suspend fun map(input: NewsEntity): PortNewsEntity = with(input) {
        PortNewsEntity(
            id,
            title,
            imageUrl,
            description
        )
    }
}