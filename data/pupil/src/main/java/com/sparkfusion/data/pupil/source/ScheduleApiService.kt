package com.sparkfusion.data.pupil.source

import com.sparkfusion.data.pupil.entity.magazine.LessonWithPassStatusDataEntity
import com.sparkfusion.data.pupil.entity.schedule.LessonDataEntity
import com.sparkfusion.data.pupil.entity.schedule.ScheduleDataEntity
import com.sparkfusion.data.pupil.entity.schedule.ScheduleWithLessonsDataEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalDate

internal interface ScheduleApiService {

    @GET("/pupil/schedule")
    suspend fun readSchedules(): Response<List<ScheduleDataEntity>>

    @GET("/pupil/schedule/withLessons")
    suspend fun readScheduleWithLessons(@Query("id") id: Int): Response<ScheduleWithLessonsDataEntity>

    @GET("/pupil/schedule/withPasses")
    suspend fun readScheduleWithPasses(
        @Query("groupMemberId") groupMemberId: Int,
        @Query("date") date: LocalDate
    ): Response<List<LessonWithPassStatusDataEntity>>

    @GET("/pupil/schedule/today")
    suspend fun readTodaySchedule(
        @Query("currentDate") currentDate: LocalDate
    ): Response<List<LessonDataEntity>>
}




























