package com.sparkfusion.portservices

data class ServiceEntity(
    val id: Long,
    val title: String,
    val imagePath: String,
    val position: Int,
    val isEnabled: Boolean
)
