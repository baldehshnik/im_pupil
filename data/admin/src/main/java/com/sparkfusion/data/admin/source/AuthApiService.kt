package com.sparkfusion.data.admin.source

import com.sparkfusion.dataPort.admin.portAuth.entity.EducationalInstitutionEntity
import com.sparkfusion.dataPort.admin.portAuth.entity.JwtAuthenticationResponseEntity
import com.sparkfusion.dataPort.admin.portAuth.entity.SignInAdminEntity
import com.sparkfusion.dataPort.admin.portAuth.entity.SignUpAdminEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApiService {

    @POST("/auth/admin/sign-up")
    suspend fun adminSignUp(@Body request: SignUpAdminEntity): Response<Unit>

    @GET("/education/institution/byNamePart")
    suspend fun readInstitutionByNamePart(
        @Query("namePart") namePart: String
    ): Response<List<EducationalInstitutionEntity>>

    @GET("/auth/check-token")
    suspend fun checkAccessToken(): Response<Unit>

    @POST("/auth/admin/sign-in")
    suspend fun adminSignIn(@Body request: SignInAdminEntity): Response<JwtAuthenticationResponseEntity>
}
























