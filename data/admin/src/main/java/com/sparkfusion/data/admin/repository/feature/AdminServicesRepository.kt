package com.sparkfusion.data.admin.repository.feature

import com.sparkfusion.core.common.api.ApiListResponseHandler
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.admin.mapper.feature.services.ServicesMapper
import com.sparkfusion.data.base.db.dao.ServiceDao
import com.sparkfusion.data.common.service.NewsImPupilApiService
import com.sparkfusion.data.commonentity.CommonNewsDataEntity
import com.sparkfusion.dataport.admin.portservices.IAdminServicesRepository
import com.sparkfusion.dataport.admin.portservices.ServiceEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdminServicesRepository @Inject constructor(
    private val serviceDao: ServiceDao,
    private val newsService: NewsImPupilApiService,
    private val servicesMapper: ServicesMapper,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : IAdminServicesRepository {

    override val enabledServices: Flow<List<ServiceEntity>>
        get() = serviceDao.readEnabledServices().map { services ->
            services.map { servicesMapper.map(it) }
        }

    override suspend fun loadNews(): Answer<List<CommonNewsDataEntity>> =
        safeApiCall(ioDispatcher) {
            val executor = ApiListResponseHandler(newsService.loadNews())
            executor.handleFetchedData()
        }
}






















