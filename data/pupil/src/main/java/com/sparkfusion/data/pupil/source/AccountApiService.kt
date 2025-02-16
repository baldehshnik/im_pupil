package com.sparkfusion.data.pupil.source

import com.sparkfusion.data.pupil.entity.account.ReadPupilAccountDataEntity
import retrofit2.Response
import retrofit2.http.GET

internal interface AccountApiService {

    @GET("/pupil/account")
    suspend fun readPupilAccount(): Response<ReadPupilAccountDataEntity?>
}

























