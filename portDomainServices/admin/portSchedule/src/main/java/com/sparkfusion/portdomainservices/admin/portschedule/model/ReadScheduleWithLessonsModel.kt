package com.sparkfusion.portdomainservices.admin.portschedule.model

import java.time.Instant

data class ReadScheduleWithLessonsModel(
    val id: Int,
    val name: String,
    val finishDate: Instant,
    val startType: Int,
    val startDate: Instant,
    val type: Int,
    val lessons: List<ReadLessonModel>
)
