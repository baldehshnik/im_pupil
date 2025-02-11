package com.sparkfusion.domainadminservices.magazine.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portmagazine.entity.ReadWeekDayPassEntity
import com.sparkfusion.portdomainservices.admin.portmagazine.model.ReadWeekDayPassModel
import javax.inject.Inject

internal class ReadWeekDayPassEntityMapper @Inject constructor(
): Mapper<ReadWeekDayPassEntity, ReadWeekDayPassModel> {

    override suspend fun map(input: ReadWeekDayPassEntity): ReadWeekDayPassModel = with(input) {
        ReadWeekDayPassModel(dayOfWeek, passesCount)
    }
}