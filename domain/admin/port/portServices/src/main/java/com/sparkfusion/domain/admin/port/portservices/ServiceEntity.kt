package com.sparkfusion.domain.admin.port.portservices

data class ServiceEntity(
    val id: Long,
    val title: String,
    val imagePath: String,
    val position: Int,
    val isEnabled: Boolean,
    val destination: ServiceDestination
)
