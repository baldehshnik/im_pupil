package com.sparkfusion.data.admin.repository.institution

import com.sparkfusion.core.common.api.ApiResponseHandler
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.admin.source.AccountApiService
import com.sparkfusion.dataport.admin.portadmindetails.AdminDetailsEntity
import com.sparkfusion.dataport.admin.portadmindetails.IAdminDetailsRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AdminDetailsRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val accountApiService: AccountApiService
): IAdminDetailsRepository {

    override suspend fun readAdminDetails(id: Int): Answer<AdminDetailsEntity> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(accountApiService.readAdminDetailsById(id))
            .handleFetchedData()
    }

    override suspend fun updateAccessOfAdmin(id: Int): Answer<Unit> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(accountApiService.updateAccessOfAdmin(id))
            .handleFetchedData()
    }

    override suspend fun deleteAdmin(id: Int): Answer<Unit> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(accountApiService.deleteAdmin(id))
            .handleFetchedData()
    }
}



























