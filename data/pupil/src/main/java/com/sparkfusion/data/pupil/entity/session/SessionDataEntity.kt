package com.sparkfusion.data.pupil.entity.session

import com.google.gson.annotations.JsonAdapter
import com.sparkfusion.core.common.api.adapter.InstantAdapter
import java.time.Instant

internal data class SessionDataEntity(
    val id: Int,
    val type: Int,
    val name: String,
    val audience: String,
    @JsonAdapter(InstantAdapter::class) val dateTime: Instant,
    val status: Int
)
