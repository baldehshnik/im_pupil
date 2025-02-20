package com.sparkfusion.data.pupil.mapper.magazine

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.pupil.entity.schedule.LessonDataEntity
import com.sparkfusion.dataport.pupil.portmagazine.LessonEntity
import javax.inject.Inject

internal class LessonDataEntityMapper @Inject constructor(
): Mapper<LessonDataEntity, LessonEntity> {

    override suspend fun map(input: LessonDataEntity): LessonEntity = with(input) {
        LessonEntity(id, name, start, end, teacher, audience, type, dayofweek, weekType)
    }
}