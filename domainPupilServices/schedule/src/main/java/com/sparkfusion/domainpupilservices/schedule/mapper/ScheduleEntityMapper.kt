package com.sparkfusion.domainpupilservices.schedule.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.portschedule.ScheduleEntity
import com.sparkfusion.portdomainservices.pupil.portschedule.ScheduleModel
import javax.inject.Inject

internal class ScheduleEntityMapper @Inject constructor(
): Mapper<ScheduleEntity, ScheduleModel> {

    override suspend fun map(input: ScheduleEntity): ScheduleModel = with(input) {
        ScheduleModel(id, name, finishDate, startType, startDate, type)
    }
}