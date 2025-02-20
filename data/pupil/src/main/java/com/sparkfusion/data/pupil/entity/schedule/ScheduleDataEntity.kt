package com.sparkfusion.data.pupil.entity.schedule

import com.google.gson.annotations.JsonAdapter
import com.sparkfusion.core.common.api.adapter.InstantAdapter
import java.time.Instant

internal data class ScheduleDataEntity(
    val id: Int,
    val name: String,
    @JsonAdapter(InstantAdapter::class) val finishDate: Instant,
    val startType: Int,
    @JsonAdapter(InstantAdapter::class) val startDate: Instant,
    val type: Int
)
