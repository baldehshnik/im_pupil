package com.sparkfusion.domain.pupil.port.portauth.model

data class JwtAuthenticationResponseModel(
    val accessToken: String,
    val refreshToken: String
)
