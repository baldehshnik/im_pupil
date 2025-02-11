package com.sparkfusion.domain.pupil.port.portauth.model

data class AddPupilModel(
    val code: String,
    val email: String,
    val password: String,
    val institutionId: Int
)
