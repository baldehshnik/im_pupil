package com.sparkfusion.data.admin.repository.auth

import com.sparkfusion.core.common.api.ApiListResponseHandler
import com.sparkfusion.core.common.api.ApiResponseHandler
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.admin.source.AuthApiService
import com.sparkfusion.dataPort.admin.portAuth.entity.EducationalInstitutionEntity
import com.sparkfusion.dataPort.admin.portAuth.entity.JwtAuthenticationResponseEntity
import com.sparkfusion.dataPort.admin.portAuth.entity.SignInAdminEntity
import com.sparkfusion.dataPort.admin.portAuth.entity.SignUpAdminEntity
import com.sparkfusion.dataPort.admin.portAuth.repository.IAdminAuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdminAuthRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val authApiService: AuthApiService
): IAdminAuthRepository {

    override suspend fun signUp(signUpAdminEntity: SignUpAdminEntity): Answer<Unit> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(authApiService.adminSignUp(signUpAdminEntity))
            .handleFetchedData()
            .suspendMap { }
    }

    override suspend fun signIn(signInAdminEntity: SignInAdminEntity): Answer<JwtAuthenticationResponseEntity> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(authApiService.adminSignIn(signInAdminEntity))
            .handleFetchedData()
    }

    override suspend fun checkToken(): Answer<Unit> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(authApiService.checkAccessToken())
            .handleFetchedData()
    }

    override suspend fun readInstitutionByNamePart(namePart: String): Answer<List<EducationalInstitutionEntity>> = safeApiCall(ioDispatcher) {
        ApiListResponseHandler(authApiService.readInstitutionByNamePart(namePart))
            .handleFetchedData()
    }
}
















