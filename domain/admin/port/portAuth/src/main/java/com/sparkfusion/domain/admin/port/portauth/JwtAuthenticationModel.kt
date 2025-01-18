package com.sparkfusion.domain.admin.port.portauth

data class JwtAuthenticationModel(
    val accessToken: String,
    val refreshToken: String
)
