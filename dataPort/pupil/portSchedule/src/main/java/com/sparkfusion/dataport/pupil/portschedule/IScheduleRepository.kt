package com.sparkfusion.dataport.pupil.portschedule

import com.sparkfusion.core.common.result.Answer

interface IScheduleRepository {

    suspend fun readSchedules(): Answer<List<ScheduleEntity>>

    suspend fun readScheduleWithLessons(id: Int): Answer<ScheduleWithLessonsEntity>
}


























