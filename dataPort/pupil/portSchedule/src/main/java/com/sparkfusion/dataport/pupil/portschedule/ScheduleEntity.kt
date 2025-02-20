package com.sparkfusion.dataport.pupil.portschedule

import java.time.Instant

data class ScheduleEntity(
    val id: Int,
    val name: String,
    val finishDate: Instant,
    val startType: Int,
    val startDate: Instant,
    val type: Int
)
