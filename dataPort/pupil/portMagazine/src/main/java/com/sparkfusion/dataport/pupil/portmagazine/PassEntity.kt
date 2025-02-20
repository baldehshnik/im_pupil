package com.sparkfusion.dataport.pupil.portmagazine

import java.time.Instant

data class PassEntity(
    val id: Int,
    val date: Instant,
    val status: Int
)
