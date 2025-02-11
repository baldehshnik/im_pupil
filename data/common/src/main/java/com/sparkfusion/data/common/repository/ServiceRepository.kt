package com.sparkfusion.data.common.repository

import com.sparkfusion.core.common.api.ApiListResponseHandler
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.common.service.AppServicesApiService
import com.sparkfusion.dataport.common.portservices.IServiceRepository
import com.sparkfusion.dataport.common.portservices.ServiceEntity
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ServiceRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val appServicesApiService: AppServicesApiService
): IServiceRepository {

    override suspend fun readServices(): Answer<List<ServiceEntity>> = safeApiCall(ioDispatcher) {
        ApiListResponseHandler(appServicesApiService.readServices())
            .handleFetchedData()
    }
}





















