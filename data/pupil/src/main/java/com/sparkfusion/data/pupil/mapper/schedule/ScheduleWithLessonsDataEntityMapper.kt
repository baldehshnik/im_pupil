package com.sparkfusion.data.pupil.mapper.schedule

import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.data.pupil.entity.schedule.ScheduleWithLessonsDataEntity
import com.sparkfusion.dataport.pupil.portschedule.ScheduleWithLessonsEntity
import javax.inject.Inject

internal class ScheduleWithLessonsDataEntityMapper @Inject constructor(
    private val lessonDataEntityMapper: LessonDataEntityMapper
) : Mapper<ScheduleWithLessonsDataEntity, ScheduleWithLessonsEntity> {

    override suspend fun map(input: ScheduleWithLessonsDataEntity): ScheduleWithLessonsEntity =
        with(input) {
            ScheduleWithLessonsEntity(
                id,
                name,
                finishDate,
                startType,
                startDate,
                type,
                lessons.map { lessonDataEntityMapper.map(it) }
            )
        }
}