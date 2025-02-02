package com.sparkfusion.data.admin.source.schedule

import com.sparkfusion.dataport.admin.portmagazine.entity.ReadWeekDayPassEntity
import com.sparkfusion.dataport.admin.portstatistics.entity.ReadFullPassEntity
import com.sparkfusion.dataport.admin.portstatistics.entity.ReadFullPassWithGroupMemberEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalDate

interface PassApiService {

    @GET("/education/schedule/lesson/passes/week")
    suspend fun readWeekStatistics(
        @Query("groupMemberId") groupMemberId: Int,
        @Query("date") date: LocalDate
    ): Response<List<ReadWeekDayPassEntity>>

    @GET("/education/schedule/lesson/passes/month")
    suspend fun readGroupMemberPassesPerMonth(
        @Query("groupMemberId") groupMemberId: Int,
        @Query("date") date: LocalDate
    ): Response<List<ReadFullPassEntity>>

    @GET("/education/schedule/lesson/passes/semester")
    suspend fun readGroupMemberPassesPerSemester(
        @Query("groupMemberId") groupMemberId: Int
    ): Response<List<ReadFullPassEntity>>

    @GET("/education/schedule/lesson/passes/group/month")
    suspend fun readPassesOfGroupPerMonth(
        @Query("groupId") groupId: Int,
        @Query("date") date: LocalDate
    ): Response<List<ReadFullPassWithGroupMemberEntity>>
}



























