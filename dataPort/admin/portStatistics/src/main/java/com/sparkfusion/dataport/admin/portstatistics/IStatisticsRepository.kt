package com.sparkfusion.dataport.admin.portstatistics

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.commonentity.CommonGroupDataEntity
import com.sparkfusion.data.commonentity.institution.CommonFacultyDataEntity
import com.sparkfusion.dataport.admin.portstatistics.entity.ReadFullPassEntity
import com.sparkfusion.dataport.admin.portstatistics.entity.ReadFullPassWithGroupMemberEntity
import com.sparkfusion.dataport.admin.portstatistics.entity.ReadGroupMemberEntity
import java.time.LocalDate

interface IStatisticsRepository {

    suspend fun readGroupByNamePart(
        institutionId: Int, namePart: String
    ): Answer<List<CommonGroupDataEntity>>

    suspend fun readGroupMembersForStatistics(groupId: Int): Answer<List<ReadGroupMemberEntity>>

    suspend fun readGroupMemberPassesPerMonth(
        groupMemberId: Int, date: LocalDate
    ): Answer<List<ReadFullPassEntity>>

    suspend fun readGroupMemberPassesPerSemester(
        groupMemberId: Int
    ): Answer<List<ReadFullPassEntity>>

    suspend fun readPassesOfGroupPerMonth(
        groupId: Int, date: LocalDate
    ): Answer<List<ReadFullPassWithGroupMemberEntity>>
}



























