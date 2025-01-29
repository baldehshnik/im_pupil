package com.sparkfusion.dataport.admin.portexam

import com.google.gson.annotations.JsonAdapter
import com.sparkfusion.core.common.api.adapter.InstantAdapter
import java.time.Instant

data class AddExamEntity(
    val groupId: Int,
    val type: Int,
    val name: String,
    val audience: String,
    @JsonAdapter(InstantAdapter::class) val dateTime: Instant
)
