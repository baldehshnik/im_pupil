package com.sparkfusion.data.common.repository

import com.sparkfusion.core.common.api.ApiListResponseHandler
import com.sparkfusion.core.common.api.ApiResponseHandler
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.common.mapper.NewsDataEntityMapper
import com.sparkfusion.data.common.service.NewsImPupilApiService
import com.sparkfusion.data.commonentity.CommonNewsInfoDataEntity
import com.sparkfusion.dataPort.common.portnews.INewsRepository
import com.sparkfusion.dataPort.common.portnews.NewsEntity
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class NewsRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val newsImPupilApiService: NewsImPupilApiService,
    private val newsDataEntityMapper: NewsDataEntityMapper
): INewsRepository {

    override suspend fun loadNewsById(id: Int): Answer<CommonNewsInfoDataEntity> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(newsImPupilApiService.loadNewsById(id))
            .handleFetchedData()
            .suspendMap { it }
    }

    override suspend fun loadNews(): Answer<List<NewsEntity>> = safeApiCall(ioDispatcher) {
        ApiListResponseHandler(newsImPupilApiService.loadNews())
            .handleFetchedData()
            .suspendMap { list -> list.map { newsDataEntityMapper.map(it) } }
    }
}















