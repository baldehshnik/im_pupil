package com.sparkfusion.data.admin.source

import com.sparkfusion.dataport.admin.portpractice.entity.ReadListPracticeEntity
import com.sparkfusion.dataport.admin.portpractice.entity.ReadPracticeEntity
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

internal interface PracticeApiService {

    @GET("/education/practice/search/{id}")
    suspend fun readPracticeById(
        @Path("id") id: Int
    ): Response<ReadPracticeEntity>

    @DELETE("/education/practice/{id}")
    suspend fun deletePracticeById(
        @Path("id") id: Int
    ): Response<Unit>

    @GET("/education/practice/search/byInstitution/{institutionId}")
    suspend fun readPractices(
        @Path("institutionId") institutionId: Int
    ): Response<List<ReadListPracticeEntity>>

    @Multipart
    @POST("/education/practice/register")
    suspend fun createPractice(
        @Part("practice") practice: RequestBody,
        @Part image: MultipartBody.Part
    ): Response<Unit>

    @Multipart
    @POST("/education/practice/update")
    suspend fun updatePractice(
        @Part("practice") practice: RequestBody,
        @Part image: MultipartBody.Part?
    ): Response<Unit>
}





























