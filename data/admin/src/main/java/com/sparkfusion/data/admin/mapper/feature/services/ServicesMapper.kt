package com.sparkfusion.data.admin.mapper.feature.services

import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.mapper.Mapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.sparkfusion.data.base.db.entity.ServiceEntity as ServiceDataEntity
import com.sparkfusion.dataport.admin.portservices.ServiceEntity as ServiceDataPortEntity

class ServicesMapper @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : Mapper<ServiceDataEntity, ServiceDataPortEntity> {

    override suspend fun map(input: ServiceDataEntity): ServiceDataPortEntity =
        withContext(ioDispatcher) {
            return@withContext with(input) {
                ServiceDataPortEntity(id, title, imagePath, position, isEnabled, destination)
            }
        }
}