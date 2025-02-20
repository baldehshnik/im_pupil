package com.sparkfusion.data.pupil.source

import com.sparkfusion.data.pupil.entity.statistics.PassDataEntity
import com.sparkfusion.data.pupil.entity.statistics.PassWithGroupMemberDataEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalDate

internal interface StatisticsApiService {

    @GET("/pupil/passes/group/month")
    suspend fun readPassesOfGroupPerMonth(
        @Query("date") date: LocalDate
    ): Response<List<PassWithGroupMemberDataEntity>>

    @GET("/education/schedule/lesson/passes/month")
    suspend fun readGroupMemberPassesPerMonth(
        @Query("groupMemberId") groupMemberId: Int,
        @Query("date") date: LocalDate
    ): Response<List<PassDataEntity>>

    @GET("/education/schedule/lesson/passes/semester")
    suspend fun readGroupMemberPassesPerSemester(
        @Query("groupMemberId") groupMemberId: Int
    ): Response<List<PassDataEntity>>
}

























