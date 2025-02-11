package com.sparkfusion.data.admin.entity

import com.google.gson.annotations.JsonAdapter
import com.sparkfusion.core.common.api.adapter.InstantAdapter
import com.sparkfusion.data.commonentity.schedule.CommonScheduleDataEntity
import java.time.Instant

internal data class ScheduleDataEntity(
    override val id: Int,
    override val name: String,
    @JsonAdapter(InstantAdapter::class) override val finishDate: Instant,
    override val startType: Int,
    @JsonAdapter(InstantAdapter::class) override val startDate: Instant,
    override val type: Int
): CommonScheduleDataEntity
