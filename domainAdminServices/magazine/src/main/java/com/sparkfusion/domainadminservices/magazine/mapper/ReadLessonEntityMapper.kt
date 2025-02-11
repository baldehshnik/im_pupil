package com.sparkfusion.domainadminservices.magazine.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portmagazine.entity.ReadLessonEntity
import com.sparkfusion.portdomainservices.admin.portmagazine.model.ReadLessonModel
import javax.inject.Inject

internal class ReadLessonEntityMapper @Inject constructor(
): Mapper<ReadLessonEntity, ReadLessonModel> {

    override suspend fun map(input: ReadLessonEntity): ReadLessonModel = with(input) {
        ReadLessonModel(id, name, start, end, teacher, audience, type, dayofweek, weekType)
    }
}