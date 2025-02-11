package com.sparkfusion.data.admin.source.schedule

import com.sparkfusion.dataport.admin.portmagazine.entity.ReadGroupMemberWithPassesEntity
import com.sparkfusion.dataport.admin.portmagazine.entity.UpdatePassStatusEntity
import com.sparkfusion.dataport.admin.portmagazine.entity.UpdatePassesStatusEntity
import com.sparkfusion.dataport.admin.portschedule.entity.ReadLessonEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.time.LocalDate

internal interface LessonApiService {

    @GET("/education/schedule/lesson/all")
    suspend fun readLessonsByScheduleId(
        @Query("scheduleId") scheduleId: Int
    ): Response<List<ReadLessonEntity>>

    @GET("/education/schedule/today")
    suspend fun readTodaySchedule(
        @Query("groupId") groupId: Int,
        @Query("currentDate") currentDate: LocalDate
    ): Response<List<com.sparkfusion.dataport.admin.portmagazine.entity.ReadLessonEntity>>

    @GET("/education/schedule/lesson/passes")
    suspend fun readPasses(
        @Query("groupId") groupId: Int,
        @Query("lessonId") lessonId: Int,
        @Query("date") date: LocalDate
    ): Response<List<ReadGroupMemberWithPassesEntity>>

    @GET("/education/schedule/lesson/pass")
    suspend fun readPassOfGroupMember(
        @Query("groupMemberId") groupMemberId: Int,
        @Query("lessonId") lessonId: Int,
        @Query("date") date: LocalDate
    ): Response<ReadGroupMemberWithPassesEntity>

    @POST("/education/schedule/lesson/pass/update")
    suspend fun updatePassOfGroupMember(
        @Body updatePassStatusEntity: UpdatePassStatusEntity
    ): Response<Unit>

    @POST("/education/schedule/lesson/passes/update")
    suspend fun updatePassesOfGroupMember(
        @Body updatePassesStatusEntity: UpdatePassesStatusEntity
    ): Response<Unit>
}




























