package com.sparkfusion.dataport.admin.portinstitutionevent

data class UpdateInstitutionEventEntity(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val duration: Int,
    val type: Int
)
