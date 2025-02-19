package com.sparkfusion.data.pupil.source

import com.sparkfusion.data.pupil.entity.practice.PracticeInfoDataEntity
import com.sparkfusion.data.pupil.entity.practice.PracticeListDataEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PracticeApiService {

    @GET("/pupil/practice")
    suspend fun readPractices(): Response<List<PracticeListDataEntity>>

    @GET("/pupil/practice/{id}")
    suspend fun readPracticeById(@Path("id") id: Int): Response<PracticeInfoDataEntity>
}






























