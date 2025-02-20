package com.sparkfusion.data.pupil.entity.statistics

import com.google.gson.annotations.JsonAdapter
import com.sparkfusion.core.common.api.adapter.InstantAdapter
import com.sparkfusion.data.pupil.entity.schedule.LessonDataEntity
import java.time.Instant

internal data class PassDataEntity(
    val id: Int,
    @JsonAdapter(InstantAdapter::class) val date: Instant,
    val lesson: LessonDataEntity
)
