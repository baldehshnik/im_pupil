package com.sparkfusion.domain.admin.services.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.common.portservices.ServiceEntity
import com.sparkfusion.domain.admin.port.portservices.ServiceDestination
import javax.inject.Inject
import com.sparkfusion.domain.admin.port.portservices.ServiceEntity as DomainPortServiceEntity

internal class DataPortServicesMapper @Inject constructor(
) : Mapper<ServiceEntity, DomainPortServiceEntity?> {

    override suspend fun map(input: ServiceEntity): DomainPortServiceEntity? = with(input) {
        val destination = ServiceDestination.entries.firstOrNull { it.value == destination }
        destination?.let {
            DomainPortServiceEntity(id, title, imagePath, position, isEnabled, it)
        }
    }
}