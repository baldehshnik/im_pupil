package com.sparkfusion.domain.admin.port.portservices

data class NewsEntity(
    val id: Int,
    val title: String,
    val imageUrl: String?,
    val description: String?
)
