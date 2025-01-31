package com.sparkfusion.portdomainservices.admin.portschedule.model

import java.time.LocalTime

data class AddLessonModel(
    val name: String,
    val start: LocalTime,
    val end: LocalTime,
    val teacher: String,
    val audience: String,
    val type: Int,
    val dayofweek: Int,
    val weekType: Int
)
