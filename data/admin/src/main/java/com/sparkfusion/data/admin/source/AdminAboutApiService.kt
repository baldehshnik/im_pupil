package com.sparkfusion.data.admin.source

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

internal interface AdminAboutApiService {

    @Multipart
    @POST("/education/about/update")
    suspend fun updateAboutBlock(
        @Part("aboutId") aboutId: Int?,
        @Part("description") description: String?,
        @Part("icon") icon: String?,
        @Part image: MultipartBody.Part?
    ): Response<Unit>
}



























