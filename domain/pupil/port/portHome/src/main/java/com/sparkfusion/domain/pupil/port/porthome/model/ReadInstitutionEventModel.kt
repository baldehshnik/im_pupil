package com.sparkfusion.domain.pupil.port.porthome.model

data class ReadInstitutionEventModel(
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val duration: Int,
    val type: Int
)
