package com.sparkfusion.domainadminservices.schedule.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portschedule.entity.AddLessonEntity
import com.sparkfusion.portdomainservices.admin.portschedule.model.AddLessonModel
import javax.inject.Inject

internal class AddLessonModelMapper @Inject constructor(
) : Mapper<AddLessonModel, AddLessonEntity> {

    override suspend fun map(input: AddLessonModel): AddLessonEntity = with(input) {
        AddLessonEntity(name, start, end, teacher, audience, type, dayofweek, weekType)
    }
}