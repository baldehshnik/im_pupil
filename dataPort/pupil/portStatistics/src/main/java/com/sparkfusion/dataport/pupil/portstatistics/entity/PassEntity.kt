package com.sparkfusion.dataport.pupil.portstatistics.entity

import java.time.Instant

data class PassEntity(
    val id: Int,
    val date: Instant,
    val lesson: LessonEntity
)
