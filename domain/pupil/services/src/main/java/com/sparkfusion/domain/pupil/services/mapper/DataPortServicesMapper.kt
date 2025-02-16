package com.sparkfusion.domain.pupil.services.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.common.portservices.ServiceEntity
import com.sparkfusion.domain.pupil.port.portservices.model.ServiceDestination
import com.sparkfusion.domain.pupil.port.portservices.model.ServiceModel
import javax.inject.Inject

internal class DataPortServicesMapper @Inject constructor(
) : Mapper<ServiceEntity, ServiceModel?> {

    override suspend fun map(input: ServiceEntity): ServiceModel? = with(input) {
        val destination = ServiceDestination.entries.firstOrNull { it.value == destination }
        destination?.let {
            ServiceModel(id, title, imagePath, position, isEnabled, it)
        }
    }
}