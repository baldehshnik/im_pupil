package com.sparkfusion.portdomainservices.pupil.portschedule

import java.time.LocalTime

data class LessonModel(
    val id: Int,
    val name: String,
    val start: LocalTime,
    val end: LocalTime,
    val teacher: String,
    val audience: String,
    val type: Int,
    val dayofweek: Int,
    val weekType: Int
)
