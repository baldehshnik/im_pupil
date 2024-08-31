package com.sparkfusion.domain.admin.port.portservices

import androidx.annotation.DrawableRes

data class ServiceEntity(
    val id: Long,
    val title: String,
    @DrawableRes val imageId: Int,
    val position: Int,
    val isEnabled: Boolean
)
