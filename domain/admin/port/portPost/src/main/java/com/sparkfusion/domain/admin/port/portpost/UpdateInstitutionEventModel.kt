package com.sparkfusion.domain.admin.port.portpost

data class UpdateInstitutionEventModel(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val duration: Int,
    val type: Int
)
