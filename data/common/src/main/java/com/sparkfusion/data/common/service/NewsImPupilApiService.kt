package com.sparkfusion.data.common.service

import com.sparkfusion.data.common.entity.NewsEntity
import retrofit2.Response
import retrofit2.http.GET

interface NewsImPupilApiService {

    @GET("/education/news/findAll")
    suspend fun loadNews(): Response<List<NewsEntity>>
}