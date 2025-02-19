package com.sparkfusion.data.pupil.repository

import com.sparkfusion.core.common.api.ApiListResponseHandler
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.pupil.mapper.about.AboutDataEntityMapper
import com.sparkfusion.data.pupil.source.AboutApiService
import com.sparkfusion.dataport.pupil.portabout.AboutEntity
import com.sparkfusion.dataport.pupil.portabout.IAboutRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AboutRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val aboutApiService: AboutApiService,
    private val aboutDataEntityMapper: AboutDataEntityMapper
) : IAboutRepository {

    override suspend fun readAboutBlocks(): Answer<List<AboutEntity>> = safeApiCall(ioDispatcher) {
        ApiListResponseHandler(aboutApiService.readAboutBlocks())
            .handleFetchedData()
            .suspendMap { list ->
                list.map {
                    aboutDataEntityMapper.map(it)
                }
            }
    }
}

















