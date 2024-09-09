package com.sparkfusion.domain.admin.services.mapper

import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.commonentity.CommonNewsDataEntity
import com.sparkfusion.domain.admin.port.portservices.NewsEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsDataEntityToNewsEntityMapper @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : Mapper<CommonNewsDataEntity, NewsEntity> {

    override suspend fun map(input: CommonNewsDataEntity): NewsEntity = withContext(ioDispatcher) {
        return@withContext NewsEntity(input.title, input.imageUrl, input.description)
    }
}