package com.sparkfusion.data.admin.source

import com.sparkfusion.dataPort.admin.portaccount.AdminEntity
import com.sparkfusion.dataPort.admin.portaccount.InstitutionAdminEntity
import retrofit2.Response
import retrofit2.http.GET

interface AccountApiService {

    @GET("/admin/account/search")
    suspend fun readAdminAccount(): Response<AdminEntity>

    @GET("/admin/all")
    suspend fun readAdminsOfInstitution(): Response<List<InstitutionAdminEntity>>
}

























