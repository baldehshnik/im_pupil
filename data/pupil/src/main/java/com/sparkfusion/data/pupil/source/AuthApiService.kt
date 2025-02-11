package com.sparkfusion.data.pupil.source

import com.sparkfusion.dataport.pupil.portauth.entity.AddPupilEntity
import com.sparkfusion.dataport.pupil.portauth.entity.EducationalInstitutionEntity
import com.sparkfusion.dataport.pupil.portauth.entity.JwtAuthenticationResponseEntity
import com.sparkfusion.dataport.pupil.portauth.entity.SignInEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

internal interface AuthApiService {

    @POST("/auth/pupil/sign-up")
    suspend fun signUpPupil(@Body addPupil: AddPupilEntity): Response<Unit>

    @POST("/auth/pupil/sign-in")
    suspend fun signInPupil(@Body signIn: SignInEntity): Response<JwtAuthenticationResponseEntity>

    @GET("/auth/check-token")
    suspend fun checkAccessToken(): Response<Unit>

    @GET("/education/institution/byNamePart")
    suspend fun readInstitutionByNamePart(
        @Query("namePart") namePart: String
    ): Response<List<EducationalInstitutionEntity>>
}
































