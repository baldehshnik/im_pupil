package com.sparkfusion.features.admin.services.entity

import com.sparkfusion.domain.admin.port.portservices.ServiceEntity

val EmptyServiceEntity = ServiceEntity(
    id = -1,
    title = "",
    imageId = -1,
    position = -1,
    isEnabled = true
)

val emptyServices = List(11) { EmptyServiceEntity }