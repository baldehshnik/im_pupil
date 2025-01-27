package com.sparkfusion.data.common.repository

import com.sparkfusion.core.common.api.ApiListResponseHandler
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.common.service.AboutApiService
import com.sparkfusion.dataport.common.portabout.IAboutRepository
import com.sparkfusion.dataport.common.portabout.ReadAboutEntity
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AboutRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val aboutApiService: AboutApiService
) : IAboutRepository {

    override suspend fun readAboutBlocksOfInstitution(institutionId: Int): Answer<List<ReadAboutEntity>> =
        safeApiCall(ioDispatcher) {
            ApiListResponseHandler(aboutApiService.readAboutBlocksOfInstitution(institutionId))
                .handleFetchedData()
                .suspendMap { it.map { model -> ReadAboutEntity(model.id, model.description, model.icon) } }
        }
}

























