package com.sparkfusion.domain.pupil.services.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataPort.common.portnews.NewsEntity
import com.sparkfusion.domain.pupil.port.portservices.model.NewsModel
import javax.inject.Inject

internal class NewsDataEntityToNewsEntityMapper @Inject constructor(
) : Mapper<NewsEntity, NewsModel> {

    override suspend fun map(input: NewsEntity): NewsModel {
        return NewsModel(input.id, input.title, input.imageUrl, input.description)
    }
}
















