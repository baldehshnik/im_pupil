package com.sparkfusion.data.pupil.entity.magazine

import com.google.gson.annotations.JsonAdapter
import com.sparkfusion.core.common.api.adapter.LocalDateAdapter
import java.time.LocalDate

internal data class UpdatePassStatusDataEntity(
    val id: Int?,
    val groupMemberId: Int,
    val lessonId: Int,
    @JsonAdapter(LocalDateAdapter::class) val date: LocalDate,
    val status: Int
)
