package com.sparkfusion.data.pupil.source

import com.sparkfusion.data.pupil.entity.section.SectionDataEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SectionApiService {

    @GET("/pupil/section")
    suspend fun readSections(): Response<List<SectionDataEntity>>

    @GET("/pupil/section/{id}")
    suspend fun readSectionById(@Path("id") id: Int): Response<SectionDataEntity>
}