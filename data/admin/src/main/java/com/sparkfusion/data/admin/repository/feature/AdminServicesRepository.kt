package com.sparkfusion.data.admin.repository.feature

import com.sparkfusion.data.admin.mapper.feature.services.ServicesMapper
import com.sparkfusion.data.base.db.dao.ServiceDao
import com.sparkfusion.portservices.IAdminServicesRepository
import com.sparkfusion.portservices.ServiceEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AdminServicesRepository @Inject constructor(
    private val serviceDao: ServiceDao,
    private val servicesMapper: ServicesMapper
) : IAdminServicesRepository {

    override val enabledServices: Flow<List<ServiceEntity>>
        get() = serviceDao.readEnabledServices().map { services ->
            services.map { servicesMapper.map(it) }
        }
}