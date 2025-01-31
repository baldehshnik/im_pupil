package com.sparkfusion.domainadminservices.schedule.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portschedule.entity.ReadLessonEntity
import com.sparkfusion.portdomainservices.admin.portschedule.model.ReadLessonModel
import javax.inject.Inject

class ReadLessonEntityMapper @Inject constructor(
): Mapper<ReadLessonEntity, ReadLessonModel> {

    override suspend fun map(input: ReadLessonEntity): ReadLessonModel = with(input) {
        ReadLessonModel(id, name, start, end, teacher, audience, type, dayofweek, weekType)
    }
}