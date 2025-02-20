package com.sparkfusion.data.pupil.mapper.magazine

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.pupil.entity.magazine.WeekDayPassDataEntity
import com.sparkfusion.dataport.pupil.portmagazine.WeekDayPassEntity
import javax.inject.Inject

internal class WeekDayPassDataEntityMapper @Inject constructor(
) : Mapper<WeekDayPassDataEntity, WeekDayPassEntity> {

    override suspend fun map(input: WeekDayPassDataEntity): WeekDayPassEntity = with(input) {
        WeekDayPassEntity(dayOfWeek, passesCount)
    }
}