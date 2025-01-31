package com.sparkfusion.portdomainservices.admin.portschedule.model

import java.time.Instant

data class AddScheduleModel(
    val groupId: Int,
    val name: String,
    val finishDate: Instant,
    val startType: Int,
    val startDate: Instant,
    val lessons: List<AddLessonModel>
)
