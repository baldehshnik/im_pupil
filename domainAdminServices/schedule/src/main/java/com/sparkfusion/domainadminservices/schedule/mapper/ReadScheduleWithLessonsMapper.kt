package com.sparkfusion.domainadminservices.schedule.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.admin.portschedule.entity.ReadScheduleWithLessonsEntity
import com.sparkfusion.portdomainservices.admin.portschedule.model.ReadScheduleWithLessonsModel
import javax.inject.Inject

internal class ReadScheduleWithLessonsMapper @Inject constructor(
    private val readLessonEntityMapper: ReadLessonEntityMapper
): Mapper<ReadScheduleWithLessonsEntity, ReadScheduleWithLessonsModel> {

    override suspend fun map(input: ReadScheduleWithLessonsEntity): ReadScheduleWithLessonsModel = with(input) {
        ReadScheduleWithLessonsModel(
            id,
            name, finishDate,
            startType,
            startDate,
            type,
            lessons.map { readLessonEntityMapper.map(it) }
        )
    }
}