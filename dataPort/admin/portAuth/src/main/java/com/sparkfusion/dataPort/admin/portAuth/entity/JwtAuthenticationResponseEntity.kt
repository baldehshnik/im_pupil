package com.sparkfusion.dataPort.admin.portAuth.entity

data class JwtAuthenticationResponseEntity(
    val accessToken: String,
    val refreshToken: String
)
