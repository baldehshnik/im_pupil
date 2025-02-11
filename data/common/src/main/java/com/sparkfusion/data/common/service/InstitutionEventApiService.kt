package com.sparkfusion.data.common.service

import com.sparkfusion.data.common.entity.InstitutionEventDataEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface InstitutionEventApiService {

    @GET("/education/event/all")
    suspend fun readInstitutionEvents(@Query("institutionId") institutionId: Int): Response<List<InstitutionEventDataEntity>>

    @GET("/education/event/{eventId}")
    suspend fun readInstitutionEvent(@Path("eventId") eventId: Int): Response<InstitutionEventDataEntity>
}














