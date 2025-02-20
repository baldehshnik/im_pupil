package com.sparkfusion.data.pupil.source

import com.sparkfusion.data.pupil.entity.session.SessionDataEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.time.LocalDate

internal interface SessionApiService {

    @GET("/pupil/exam")
    suspend fun readExams(
        @Query("date") date: LocalDate
    ): Response<List<SessionDataEntity>>
}