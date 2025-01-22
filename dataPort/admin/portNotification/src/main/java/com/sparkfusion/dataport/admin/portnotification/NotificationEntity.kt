package com.sparkfusion.dataport.admin.portnotification

import com.google.gson.annotations.JsonAdapter
import com.sparkfusion.core.common.api.adapter.InstantAdapter
import java.time.Instant

data class NotificationEntity(
    val id: Int,
    val icon: String,
    val title: String,
    val description: String,
    @JsonAdapter(InstantAdapter::class) val dateTime: Instant,
    val status: Boolean,
    val type: Int
)
