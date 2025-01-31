package com.sparkfusion.data.commonentity.schedule

import java.time.Instant

interface CommonScheduleDataEntity {
    val id: Int
    val name: String
    val finishDate: Instant
    val startType: Int
    val startDate: Instant
    val type: Int
}