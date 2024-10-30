package com.sparkfusion.features.admin.services.entity

import com.sparkfusion.domain.admin.port.portservices.ServiceDestination
import com.sparkfusion.domain.admin.port.portservices.ServiceEntity

val EmptyServiceEntity = ServiceEntity(
    id = -1,
    title = "",
    imagePath = "",
    position = -1,
    isEnabled = true,
    destination = ServiceDestination.MAGAZINE
)

val emptyServices = List(4) { EmptyServiceEntity }