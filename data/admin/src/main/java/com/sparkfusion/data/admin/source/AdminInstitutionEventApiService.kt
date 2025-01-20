package com.sparkfusion.data.admin.source

import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Path

interface AdminInstitutionEventApiService {

    @DELETE("/education/event/delete/{id}")
    suspend fun deleteEventById(@Path("id") id: Int): Response<Unit>
}






















