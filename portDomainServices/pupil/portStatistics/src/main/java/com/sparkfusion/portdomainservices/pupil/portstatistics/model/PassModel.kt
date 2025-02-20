package com.sparkfusion.portdomainservices.pupil.portstatistics.model

import java.time.Instant

data class PassModel(
    val id: Int,
    val date: Instant,
    val lesson: LessonModel
)
