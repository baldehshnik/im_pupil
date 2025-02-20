package com.sparkfusion.portdomainservices.pupil.portsession

import java.time.Instant

data class SessionModel(
    val id: Int,
    val type: Int,
    val name: String,
    val audience: String,
    val dateTime: Instant,
    val status: Int
)
