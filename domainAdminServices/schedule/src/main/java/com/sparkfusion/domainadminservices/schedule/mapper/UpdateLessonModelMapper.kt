package com.sparkfusion.domainadminservices.schedule.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portschedule.entity.UpdateLessonEntity
import com.sparkfusion.portdomainservices.admin.portschedule.model.UpdateLessonModel
import javax.inject.Inject

class UpdateLessonModelMapper @Inject constructor(
) : Mapper<UpdateLessonModel, UpdateLessonEntity> {

    override suspend fun map(input: UpdateLessonModel): UpdateLessonEntity = with(input) {
        UpdateLessonEntity(id, name, start, end, teacher, audience, type, dayofweek, weekType)
    }
}