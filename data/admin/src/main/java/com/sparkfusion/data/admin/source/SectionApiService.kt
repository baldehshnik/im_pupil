package com.sparkfusion.data.admin.source

import com.sparkfusion.dataport.admin.portsections.entity.ReadSectionEntity
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface SectionApiService {

    @GET("/education/section/all")
    suspend fun readSections(
        @Query("institutionId") institutionId: Int
    ): Response<List<ReadSectionEntity>>

    @GET("/education/section/{id}")
    suspend fun readSectionById(
        @Path("id") id: Int
    ): Response<ReadSectionEntity>

    @DELETE("/education/section/{id}")
    suspend fun deleteSectionById(
        @Path("id") id: Int
    ): Response<Unit>

    @Multipart
    @POST("/education/section/create")
    suspend fun createSection(
        @Part("section") createSection: RequestBody,
        @Part image: MultipartBody.Part
    ): Response<Unit>

    @Multipart
    @POST("/education/section/update")
    suspend fun updateSection(
        @Part("section") updateSection: RequestBody,
        @Part image: MultipartBody.Part?
    ): Response<Unit>
}





























