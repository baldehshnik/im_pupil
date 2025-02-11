package com.sparkfusion.data.pupil.repository

import com.sparkfusion.core.common.api.ApiListResponseHandler
import com.sparkfusion.core.common.api.ApiResponseHandler
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.pupil.source.AuthApiService
import com.sparkfusion.dataport.pupil.portauth.IAuthRepository
import com.sparkfusion.dataport.pupil.portauth.entity.AddPupilEntity
import com.sparkfusion.dataport.pupil.portauth.entity.EducationalInstitutionEntity
import com.sparkfusion.dataport.pupil.portauth.entity.JwtAuthenticationResponseEntity
import com.sparkfusion.dataport.pupil.portauth.entity.SignInEntity
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AuthRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val authApiService: AuthApiService
) : IAuthRepository {

    override suspend fun signUpPupil(addPupil: AddPupilEntity): Answer<Unit> =
        safeApiCall(ioDispatcher) {
            ApiResponseHandler(authApiService.signUpPupil(addPupil))
                .handleFetchedData()
        }

    override suspend fun signInPupil(signIn: SignInEntity): Answer<JwtAuthenticationResponseEntity> =
        safeApiCall(ioDispatcher) {
            ApiResponseHandler(authApiService.signInPupil(signIn))
                .handleFetchedData()
        }

    override suspend fun checkAccessToken(): Answer<Unit> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(authApiService.checkAccessToken())
            .handleFetchedData()
    }

    override suspend fun readInstitutionByNamePart(namePart: String): Answer<List<EducationalInstitutionEntity>> =
        safeApiCall(ioDispatcher) {
            ApiListResponseHandler(authApiService.readInstitutionByNamePart(namePart))
                .handleFetchedData()
        }
}




























