package com.sparkfusion.data.common.service

import com.sparkfusion.dataport.common.portservices.ServiceEntity
import retrofit2.Response
import retrofit2.http.GET

internal interface AppServicesApiService {

    @GET("/services")
    suspend fun readServices(): Response<List<ServiceEntity>>
}






























