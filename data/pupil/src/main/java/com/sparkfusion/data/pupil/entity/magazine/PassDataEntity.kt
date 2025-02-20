package com.sparkfusion.data.pupil.entity.magazine

import com.google.gson.annotations.JsonAdapter
import com.sparkfusion.core.common.api.adapter.InstantAdapter
import java.time.Instant

internal data class PassDataEntity(
    val id: Int,
    @JsonAdapter(InstantAdapter::class) val date: Instant,
    val status: Int
)
