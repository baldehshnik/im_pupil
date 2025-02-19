package com.sparkfusion.data.pupil.source

import com.sparkfusion.data.pupil.entity.about.AboutDataEntity
import retrofit2.Response
import retrofit2.http.GET

interface AboutApiService {

    @GET("/pupil/about")
    suspend fun readAboutBlocks(): Response<List<AboutDataEntity>>
}


