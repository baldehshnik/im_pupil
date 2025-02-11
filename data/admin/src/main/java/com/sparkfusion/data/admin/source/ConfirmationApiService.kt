package com.sparkfusion.data.admin.source

import com.sparkfusion.dataport.admin.portconfirmation.AdminEntity
import com.sparkfusion.dataport.admin.portconfirmation.PupilEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

internal interface ConfirmationApiService {

    @GET("/admin/notConfirmed")
    suspend fun readNotConfirmedAdmins(): Response<List<AdminEntity>>

    @POST("/admin/confirm")
    suspend fun confirmAdmin(@Query("adminId") adminId: Int): Response<Unit>

    @GET("/pupil/notConfirmed")
    suspend fun readNotConfirmedPupils(@Query("institutionId") institutionId: Int): Response<List<PupilEntity>>

    @POST("/pupil/confirm/{id}")
    suspend fun confirmPupil(@Path("id") id: Int): Response<Unit>
}































