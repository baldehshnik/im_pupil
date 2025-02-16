package com.sparkfusion.domain.pupil.port.porteventdetails.model

data class EventDetailsModel(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val duration: Int,
    val type: Int
)
