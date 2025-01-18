package com.sparkfusion.data.admin.source

import com.sparkfusion.dataPort.admin.portinstitution.InstitutionEntity
import retrofit2.Response
import retrofit2.http.GET

interface InstitutionApiService {

    @GET("/education/institution/ofAdmin")
    suspend fun readInstitutionOfAdmin(): Response<InstitutionEntity>
}






























