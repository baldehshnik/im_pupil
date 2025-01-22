package com.sparkfusion.data.admin.source

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface AdminInstitutionEventApiService {

    @DELETE("/education/event/delete/{id}")
    suspend fun deleteEventById(@Path("id") id: Int): Response<Unit>

    @Multipart
    @POST("/education/event/create")
    suspend fun addEvent(
        @Part("event") event: RequestBody,
        @Part image: MultipartBody.Part
    ): Response<Unit>

    @Multipart
    @POST("/education/event/update")
    suspend fun updateEvent(
        @Part("event") event: RequestBody,
        @Part image: MultipartBody.Part?
    ): Response<Unit>
}






















