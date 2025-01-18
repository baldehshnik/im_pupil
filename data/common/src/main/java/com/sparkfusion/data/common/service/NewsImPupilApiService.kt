package com.sparkfusion.data.common.service

import com.sparkfusion.data.common.entity.NewsEntity
import com.sparkfusion.data.common.entity.NewsInfoEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsImPupilApiService {

    @GET("/news")
    suspend fun loadNews(): Response<List<NewsEntity>>

    @GET("/news/{id}")
    suspend fun loadNewsById(@Path("id") id: Int): Response<NewsInfoEntity>
}














