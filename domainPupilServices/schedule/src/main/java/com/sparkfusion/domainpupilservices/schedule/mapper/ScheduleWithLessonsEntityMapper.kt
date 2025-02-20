package com.sparkfusion.domainpupilservices.schedule.mapper

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.dataport.pupil.portschedule.ScheduleWithLessonsEntity
import com.sparkfusion.portdomainservices.pupil.portschedule.ScheduleWithLessonsModel
import javax.inject.Inject

internal class ScheduleWithLessonsEntityMapper @Inject constructor(
    private val lessonEntityMapper: LessonEntityMapper
): Mapper<ScheduleWithLessonsEntity, ScheduleWithLessonsModel> {

    override suspend fun map(input: ScheduleWithLessonsEntity): ScheduleWithLessonsModel = with(input) {
        ScheduleWithLessonsModel(
            id,
            name,
            finishDate,
            startType,
            startDate,
            type,
            lessons.map { lessonEntityMapper.map(it) }
        )
    }
}