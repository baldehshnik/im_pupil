package com.sparkfusion.dataport.admin.portmagazine.entity

import com.google.gson.annotations.JsonAdapter
import com.sparkfusion.core.common.api.adapter.LocalDateAdapter
import java.time.LocalDate

data class UpdatePassStatusEntity(
    val id: Int?,
    val groupMemberId: Int,
    val lessonId: Int,
    @JsonAdapter(LocalDateAdapter::class) val date: LocalDate,
    val status: Int
)
