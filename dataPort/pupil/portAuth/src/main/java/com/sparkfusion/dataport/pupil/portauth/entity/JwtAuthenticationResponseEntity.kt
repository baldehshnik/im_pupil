package com.sparkfusion.dataport.pupil.portauth.entity

data class JwtAuthenticationResponseEntity(
    val accessToken: String,
    val refreshToken: String
)
