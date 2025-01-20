package com.sparkfusion.data.admin.repository.institution

import com.sparkfusion.core.common.api.ApiListResponseHandler
import com.sparkfusion.core.common.api.ApiResponseHandler
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.admin.source.AccountApiService
import com.sparkfusion.dataPort.admin.portaccount.AdminEntity
import com.sparkfusion.dataPort.admin.portaccount.IAdminAccountRepository
import com.sparkfusion.dataPort.admin.portaccount.InstitutionAdminEntity
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdminAccountRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val accountApiService: AccountApiService
): IAdminAccountRepository {

    override suspend fun readAdminAccount(): Answer<AdminEntity> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(accountApiService.readAdminAccount())
            .handleFetchedData()
    }

    override suspend fun readAdminsOfInstitution(): Answer<List<InstitutionAdminEntity>> = safeApiCall(ioDispatcher) {
        ApiListResponseHandler(accountApiService.readAdminsOfInstitution())
            .handleFetchedData()
    }
}




















