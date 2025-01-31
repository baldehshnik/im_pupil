package com.sparkfusion.dataport.admin.portschedule.entity

import com.google.gson.annotations.JsonAdapter
import com.sparkfusion.core.common.api.adapter.LocalTimeAdapter
import java.time.LocalTime

data class AddLessonEntity(
    val name: String,
    @JsonAdapter(LocalTimeAdapter::class) val start: LocalTime,
    @JsonAdapter(LocalTimeAdapter::class) val end: LocalTime,
    val teacher: String,
    val audience: String,
    val type: Int,
    val dayofweek: Int,
    val weekType: Int
)
