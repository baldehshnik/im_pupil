package com.sparkfusion.data.common.service

import com.sparkfusion.data.common.entity.about.ReadAboutDataEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface AboutApiService {

    @GET("/education/about")
    suspend fun readAboutBlocksOfInstitution(
        @Query("institutionId") institutionId: Int
    ): Response<List<ReadAboutDataEntity>>
}






















