package com.sparkfusion.dataport.pupil.portstatistics

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portstatistics.entity.GroupMemberEntity
import com.sparkfusion.dataport.pupil.portstatistics.entity.PassEntity
import com.sparkfusion.dataport.pupil.portstatistics.entity.PassWithGroupMemberEntity
import java.time.LocalDate

interface IStatisticsRepository {

    suspend fun readGroupMembersForStatistics(): Answer<List<GroupMemberEntity>>
    suspend fun readGroupMemberById(id: Int): Answer<GroupMemberEntity>

    suspend fun readGroupMemberPassesPerMonth(
        groupMemberId: Int, date: LocalDate
    ): Answer<List<PassEntity>>

    suspend fun readGroupMemberPassesPerSemester(
        groupMemberId: Int
    ): Answer<List<PassEntity>>

    suspend fun readPassesOfGroupPerMonth(date: LocalDate): Answer<List<PassWithGroupMemberEntity>>
}


























