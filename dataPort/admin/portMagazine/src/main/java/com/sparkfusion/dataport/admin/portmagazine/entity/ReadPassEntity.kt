package com.sparkfusion.dataport.admin.portmagazine.entity

import com.google.gson.annotations.JsonAdapter
import com.sparkfusion.core.common.api.adapter.InstantAdapter
import java.time.Instant

data class ReadPassEntity(
    val id: Int?,
    @JsonAdapter(InstantAdapter::class) val date: Instant,
    val status: Int
)
