package com.sparkfusion.domain.admin.services.mapper

import com.sparkfusion.core.common.mapper.Mapper
import javax.inject.Inject
import com.sparkfusion.domain.admin.port.portservices.ServiceEntity as DomainPortServiceEntity
import com.sparkfusion.portservices.ServiceEntity as DataPortServiceEntity

class DataPortServicesMapper @Inject constructor() : Mapper<DataPortServiceEntity, DomainPortServiceEntity> {

    override suspend fun map(input: DataPortServiceEntity): DomainPortServiceEntity {
        return with(input) {
            DomainPortServiceEntity(id, title, imagePath, position, isEnabled)
        }
    }
}