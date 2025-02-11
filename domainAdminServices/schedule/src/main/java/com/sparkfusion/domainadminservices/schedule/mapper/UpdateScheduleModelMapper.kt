package com.sparkfusion.domainadminservices.schedule.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portschedule.entity.UpdateScheduleEntity
import com.sparkfusion.portdomainservices.admin.portschedule.model.UpdateScheduleModel
import javax.inject.Inject

internal class UpdateScheduleModelMapper @Inject constructor(
    private val updateLessonModelMapper: UpdateLessonModelMapper
) : Mapper<UpdateScheduleModel, UpdateScheduleEntity> {

    override suspend fun map(input: UpdateScheduleModel): UpdateScheduleEntity = with(input) {
        UpdateScheduleEntity(
            groupId,
            id,
            name,
            finishDate,
            startType,
            startDate,
            type,
            lessons.map { updateLessonModelMapper.map(it) }
        )
    }
}