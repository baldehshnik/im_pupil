package com.sparkfusion.data.admin.source.schedule

import com.sparkfusion.dataport.admin.portmagazine.entity.ReadWeekDayPassEntity
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
}



























