package com.sparkfusion.domain.pupil.port.portservices.model

data class ServiceModel(
    val id: Long,
    val title: String,
    val imagePath: String,
    val position: Int,
    val isEnabled: Boolean,
    val destination: ServiceDestination
)
