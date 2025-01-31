package com.sparkfusion.dataport.admin.portschedule

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.commonentity.CommonGroupDataEntity
import com.sparkfusion.data.commonentity.institution.CommonFacultyDataEntity
import com.sparkfusion.data.commonentity.schedule.CommonScheduleDataEntity
import com.sparkfusion.dataport.admin.portschedule.entity.AddScheduleEntity
import com.sparkfusion.dataport.admin.portschedule.entity.ReadLessonEntity
import com.sparkfusion.dataport.admin.portschedule.entity.ReadScheduleWithLessonsEntity
import com.sparkfusion.dataport.admin.portschedule.entity.UpdateScheduleEntity

interface IScheduleRepository {

    suspend fun readFaculties(): Answer<List<CommonFacultyDataEntity>>

    suspend fun readGroupByNamePart(
        institutionId: Int,
        namePart: String
    ): Answer<List<CommonGroupDataEntity>>

    suspend fun readLessonsByScheduleId(scheduleId: Int): Answer<List<ReadLessonEntity>>

    suspend fun readScheduleByGroupId(groupId: Int): Answer<List<CommonScheduleDataEntity>>
    suspend fun readScheduleWithLessons(id: Int): Answer<ReadScheduleWithLessonsEntity>
    suspend fun makeScheduleAsACurrent(scheduleId: Int): Answer<Unit>
    suspend fun clearScheduleStatus(scheduleId: Int): Answer<Unit>
    suspend fun createSchedule(schedule: AddScheduleEntity): Answer<Unit>
    suspend fun updateSchedule(schedule: UpdateScheduleEntity): Answer<Unit>
}










































