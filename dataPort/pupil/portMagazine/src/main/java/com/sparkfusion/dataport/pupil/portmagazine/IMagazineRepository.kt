package com.sparkfusion.dataport.pupil.portmagazine

import com.sparkfusion.core.common.result.Answer
import java.time.LocalDate

interface IMagazineRepository {

    suspend fun readPupilAccount(): Answer<AccountEntity?>

    suspend fun readGroupMembers(): Answer<List<GroupMemberEntity>>

    suspend fun readWeekStatistics(
        groupMemberId: Int, date: LocalDate
    ): Answer<List<WeekDayPassEntity>>

    suspend fun readPasses(
        lessonId: Int, date: LocalDate
    ): Answer<List<GroupMemberWithPassEntity>>

    suspend fun readPassOfGroupMember(
        groupMemberId: Int,
        lessonId: Int,
        date: LocalDate
    ): Answer<GroupMemberWithPassEntity>

    suspend fun updatePassOfGroupMember(
        updatePassStatus: UpdatePassStatusEntity
    ): Answer<Unit>

    suspend fun updatePasses(
        updatePassesStatus: UpdatePassesStatusEntity
    ): Answer<Unit>

    suspend fun readTodaySchedule(currentDate: LocalDate): Answer<List<LessonEntity>>

    suspend fun readScheduleWithPasses(
        groupMemberId: Int, date: LocalDate
    ): Answer<List<LessonWithPassStatusEntity>>
}




























