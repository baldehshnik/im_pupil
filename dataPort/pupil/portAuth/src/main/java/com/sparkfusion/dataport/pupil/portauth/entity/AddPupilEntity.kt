package com.sparkfusion.dataport.pupil.portauth.entity

data class AddPupilEntity(
    val code: String,
    val email: String,
    val password: String,
    val institutionId: Int
)
