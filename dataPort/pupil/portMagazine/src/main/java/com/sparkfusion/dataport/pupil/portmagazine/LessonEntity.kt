package com.sparkfusion.dataport.pupil.portmagazine

import java.time.LocalTime

data class LessonEntity(
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
