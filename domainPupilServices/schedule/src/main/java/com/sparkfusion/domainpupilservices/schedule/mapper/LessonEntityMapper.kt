package com.sparkfusion.domainpupilservices.schedule.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.portschedule.LessonEntity
import com.sparkfusion.portdomainservices.pupil.portschedule.LessonModel
import javax.inject.Inject

internal class LessonEntityMapper @Inject constructor(
): Mapper<LessonEntity, LessonModel> {

    override suspend fun map(input: LessonEntity): LessonModel = with(input) {
        LessonModel(id, name, start, end, teacher, audience, type, dayofweek, weekType)
    }
}