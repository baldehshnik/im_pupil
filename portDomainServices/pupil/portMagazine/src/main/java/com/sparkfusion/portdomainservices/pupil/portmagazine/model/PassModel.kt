package com.sparkfusion.portdomainservices.pupil.portmagazine.model

import java.time.Instant

data class PassModel(
    val id: Int,
    val date: Instant,
    val status: Int
)
