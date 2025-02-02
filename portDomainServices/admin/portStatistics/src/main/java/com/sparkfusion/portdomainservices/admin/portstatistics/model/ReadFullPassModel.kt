package com.sparkfusion.portdomainservices.admin.portstatistics.model

import java.time.Instant

data class ReadFullPassModel(
    val id: Int,
    val date: Instant,
    val lesson: ReadLessonModel
)
