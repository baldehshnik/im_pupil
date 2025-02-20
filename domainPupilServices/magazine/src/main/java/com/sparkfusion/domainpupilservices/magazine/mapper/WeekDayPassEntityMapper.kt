package com.sparkfusion.domainpupilservices.magazine.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.portmagazine.WeekDayPassEntity
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.WeekDayPassModel
import javax.inject.Inject

internal class WeekDayPassEntityMapper @Inject constructor(
): Mapper<WeekDayPassEntity, WeekDayPassModel> {

    override suspend fun map(input: WeekDayPassEntity): WeekDayPassModel = with(input) {
        WeekDayPassModel(dayOfWeek, passesCount)
    }
}