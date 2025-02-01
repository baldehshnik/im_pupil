package com.sparkfusion.dataport.admin.portmagazine

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.commonentity.CommonGroupDataEntity
import com.sparkfusion.data.commonentity.institution.CommonFacultyDataEntity
import com.sparkfusion.dataport.admin.portmagazine.entity.ReadGroupMemberEntity
import com.sparkfusion.dataport.admin.portmagazine.entity.ReadGroupMemberWithPassesEntity
import com.sparkfusion.dataport.admin.portmagazine.entity.ReadLessonEntity
import com.sparkfusion.dataport.admin.portmagazine.entity.ReadLessonWithPassStatusEntity
import com.sparkfusion.dataport.admin.portmagazine.entity.ReadWeekDayPassEntity
import com.sparkfusion.dataport.admin.portmagazine.entity.UpdatePassStatusEntity
import com.sparkfusion.dataport.admin.portmagazine.entity.UpdatePassesStatusEntity
import java.time.LocalDate

interface IMagazineRepository {

    suspend fun readFaculties(): Answer<List<CommonFacultyDataEntity>>

    suspend fun readGroupByNamePart(
        institutionId: Int,
        namePart: String
    ): Answer<List<CommonGroupDataEntity>>

    suspend fun readGroupMembersForMagazine(groupId: Int): Answer<List<ReadGroupMemberEntity>>

    suspend fun readLessonsWithPassStatus(
        groupMemberId: Int,
        date: LocalDate
    ): Answer<List<ReadLessonWithPassStatusEntity>>

    suspend fun readTodaySchedule(
        groupId: Int,
        currentDate: LocalDate
    ): Answer<List<ReadLessonEntity>>

    suspend fun readPasses(
        groupId: Int,
        lessonId: Int,
        date: LocalDate
    ): Answer<List<ReadGroupMemberWithPassesEntity>>

    suspend fun readPassOfGroupMember(
        groupMemberId: Int,
        lessonId: Int,
        date: LocalDate
    ): Answer<ReadGroupMemberWithPassesEntity>

    suspend fun updatePassOfGroupMember(updatePassStatusEntity: UpdatePassStatusEntity): Answer<Unit>
    suspend fun updatePassesOfGroupMember(updatePassesStatusEntity: UpdatePassesStatusEntity): Answer<Unit>
    suspend fun readWeekStatistics(
        groupMemberId: Int,
        date: LocalDate
    ): Answer<List<ReadWeekDayPassEntity>>
}


























