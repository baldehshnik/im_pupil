package com.sparkfusion.domainadminservices.schedule.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.commonentity.schedule.CommonScheduleDataEntity
import com.sparkfusion.portdomainservices.admin.portschedule.model.ScheduleModel
import javax.inject.Inject

class ScheduleEntityMapper @Inject constructor(
): Mapper<CommonScheduleDataEntity, ScheduleModel> {

    override suspend fun map(input: CommonScheduleDataEntity): ScheduleModel = with(input) {
        ScheduleModel(id, name, finishDate, startType, startDate, type)
    }
}