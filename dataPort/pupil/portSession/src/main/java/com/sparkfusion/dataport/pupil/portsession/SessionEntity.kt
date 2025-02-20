package com.sparkfusion.dataport.pupil.portsession

import java.time.Instant

data class SessionEntity(
    val id: Int,
    val type: Int,
    val name: String,
    val audience: String,
    val dateTime: Instant,
    val status: Int
)
