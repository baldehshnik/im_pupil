package com.sparkfusion.data.pupil.source

import com.sparkfusion.data.pupil.entity.ReadInstitutionEventDataEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

internal interface InstitutionEventApiService {

    @GET("/pupil/institution/event")
    suspend fun readEvents(): Response<List<ReadInstitutionEventDataEntity>>

    @GET("/pupil/institution/event/{id}")
    suspend fun readEventById(@Path("id") id: Int): Response<ReadInstitutionEventDataEntity>
}































