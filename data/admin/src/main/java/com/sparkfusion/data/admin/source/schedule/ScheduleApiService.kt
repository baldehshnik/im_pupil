package com.sparkfusion.data.admin.source.schedule

import com.sparkfusion.data.admin.entity.ScheduleDataEntity
import com.sparkfusion.dataport.admin.portschedule.entity.AddScheduleEntity
import com.sparkfusion.dataport.admin.portschedule.entity.ReadScheduleWithLessonsEntity
import com.sparkfusion.dataport.admin.portschedule.entity.UpdateScheduleEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ScheduleApiService {

    @GET("/education/schedule/all")
    suspend fun readScheduleByGroupId(
        @Query("groupId") groupId: Int
    ): Response<List<ScheduleDataEntity>>

    @GET("/education/schedule/withLessons")
    suspend fun readScheduleWithLessons(
        @Query("id") id: Int
    ): Response<ReadScheduleWithLessonsEntity>

    @POST("/education/schedule/current")
    suspend fun makeScheduleAsACurrent(
        @Query("scheduleId") scheduleId: Int
    ): Response<Unit>

    @POST("/education/schedule/clearStatus")
    suspend fun clearScheduleStatus(
        @Query("scheduleId") scheduleId: Int
    ): Response<Unit>

    @POST("/education/schedule/create")
    suspend fun createSchedule(
        @Body schedule: AddScheduleEntity
    ): Response<Unit>

    @POST("/education/schedule/update")
    suspend fun updateSchedule(
        @Body schedule: UpdateScheduleEntity
    ): Response<Unit>
}































