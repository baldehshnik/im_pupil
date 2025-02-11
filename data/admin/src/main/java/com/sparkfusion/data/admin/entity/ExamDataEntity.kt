package com.sparkfusion.data.admin.entity

import com.google.gson.annotations.JsonAdapter
import com.sparkfusion.core.common.api.adapter.InstantAdapter
import com.sparkfusion.data.commonentity.exam.CommonExamDataEntity
import java.time.Instant

internal data class ExamDataEntity(
    override val id: Int,
    override val type: Int,
    override val name: String,
    override val audience: String,
    @JsonAdapter(InstantAdapter::class) override val dateTime: Instant,
    override val status: Int
) : CommonExamDataEntity
