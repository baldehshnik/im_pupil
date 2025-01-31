package com.sparkfusion.data.admin.source.schedule

import com.sparkfusion.dataport.admin.portschedule.entity.ReadLessonEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface LessonApiService {

    @GET("/education/schedule/lesson/all")
    suspend fun readLessonsByScheduleId(
        @Query("scheduleId") scheduleId: Int
    ): Response<List<ReadLessonEntity>>

}




























