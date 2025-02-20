package com.sparkfusion.data.pupil.mapper.schedule

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.pupil.entity.schedule.ScheduleDataEntity
import com.sparkfusion.dataport.pupil.portschedule.ScheduleEntity
import javax.inject.Inject

internal class ScheduleDataEntityMapper @Inject constructor(
): Mapper<ScheduleDataEntity, ScheduleEntity> {

    override suspend fun map(input: ScheduleDataEntity): ScheduleEntity = with(input) {
        ScheduleEntity(id, name, finishDate, startType, startDate, type)
    }
}