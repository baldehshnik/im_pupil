package com.sparkfusion.data.common.repository

import com.sparkfusion.core.common.api.ApiResponseHandler
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.common.service.NewsImPupilApiService
import com.sparkfusion.data.commonentity.CommonNewsInfoDataEntity
import com.sparkfusion.dataPort.common.portnews.INewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class NewsRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val newsImPupilApiService: NewsImPupilApiService
): INewsRepository {

    override suspend fun loadNewsById(id: Int): Answer<CommonNewsInfoDataEntity> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(newsImPupilApiService.loadNewsById(id))
            .handleFetchedData()
            .suspendMap { it }
    }
}















