package com.sparkfusion.data.admin.source

import com.sparkfusion.dataPort.admin.portaccount.AdminEntity
import com.sparkfusion.dataPort.admin.portaccount.InstitutionAdminEntity
import com.sparkfusion.dataport.admin.portadmindetails.AdminDetailsEntity
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AccountApiService {

    @GET("/admin/account/search")
    suspend fun readAdminAccount(): Response<AdminEntity>

    @GET("/admin/all")
    suspend fun readAdminsOfInstitution(): Response<List<InstitutionAdminEntity>>

    @GET("/admin/account/search/{id}")
    suspend fun readAdminDetailsById(@Path("id") id: Int): Response<AdminDetailsEntity>

    @POST("/admin/account/update/access/{adminId}")
    suspend fun updateAccessOfAdmin(@Path("adminId") adminId: Int): Response<Unit>

    @DELETE("/admin/account/delete/{adminId}")
    suspend fun deleteAdmin(@Path("adminId") adminId: Int): Response<Unit>
}

























