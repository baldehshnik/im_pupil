package com.sparkfusion.dataPort.common.portinstitutionevent

data class InstitutionEventEntity(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val duration: Int,
    val type: Int
)
