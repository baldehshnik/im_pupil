package com.sparkfusion.data.admin.mapper.feature.services

import com.sparkfusion.core.common.mapper.Mapper
import javax.inject.Inject
import com.sparkfusion.data.base.db.entity.ServiceEntity as ServiceDataEntity
import com.sparkfusion.portservices.ServiceEntity as ServiceDataPortEntity

class ServicesMapper @Inject constructor() : Mapper<ServiceDataEntity, ServiceDataPortEntity> {

    override suspend fun map(input: ServiceDataEntity): ServiceDataPortEntity {
        return with(input) {
            ServiceDataPortEntity(id, title, imageId, position, isEnabled)
        }
    }
}