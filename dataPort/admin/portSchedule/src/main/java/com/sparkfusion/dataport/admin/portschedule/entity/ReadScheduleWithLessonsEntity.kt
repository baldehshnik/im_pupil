package com.sparkfusion.dataport.admin.portschedule.entity

import com.google.gson.annotations.JsonAdapter
import com.sparkfusion.core.common.api.adapter.InstantAdapter
import java.time.Instant

data class ReadScheduleWithLessonsEntity(
    val id: Int,
    val name: String,
    @JsonAdapter(InstantAdapter::class) val finishDate: Instant,
    val startType: Int,
    @JsonAdapter(InstantAdapter::class) val startDate: Instant,
    val type: Int,
    val lessons: List<ReadLessonEntity>
)
