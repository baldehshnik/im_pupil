package com.sparkfusion.domainadminservices.schedule.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portschedule.entity.AddScheduleEntity
import com.sparkfusion.portdomainservices.admin.portschedule.model.AddScheduleModel
import javax.inject.Inject

internal class AddScheduleModelMapper @Inject constructor(
    private val addLessonModelMapper: AddLessonModelMapper
): Mapper<AddScheduleModel, AddScheduleEntity> {

    override suspend fun map(input: AddScheduleModel): AddScheduleEntity = with(input) {
        AddScheduleEntity(
            groupId,
            name,
            finishDate,
            startType,
            startDate,
            lessons.map { addLessonModelMapper.map(it) }
        )
    }
}