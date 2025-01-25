package com.sparkfusion.data.admin.repository.confirmation

import com.sparkfusion.core.common.api.ApiListResponseHandler
import com.sparkfusion.core.common.api.ApiResponseHandler
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.admin.source.ConfirmationApiService
import com.sparkfusion.dataport.admin.portconfirmation.AdminEntity
import com.sparkfusion.dataport.admin.portconfirmation.IConfirmationRepository
import com.sparkfusion.dataport.admin.portconfirmation.PupilEntity
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConfirmationRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val confirmationApiService: ConfirmationApiService
): IConfirmationRepository {

    override suspend fun readNotConfirmedAdmins(): Answer<List<AdminEntity>> = safeApiCall(ioDispatcher) {
        ApiListResponseHandler(confirmationApiService.readNotConfirmedAdmins())
            .handleFetchedData()
    }

    override suspend fun readNotConfirmedPupils(institutionId: Int): Answer<List<PupilEntity>> = safeApiCall(ioDispatcher) {
        ApiListResponseHandler(confirmationApiService.readNotConfirmedPupils(institutionId))
            .handleFetchedData()
    }

    override suspend fun confirmAdmin(adminId: Int): Answer<Unit> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(confirmationApiService.confirmAdmin(adminId))
            .handleFetchedData()
    }

    override suspend fun confirmPupil(id: Int): Answer<Unit> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(confirmationApiService.confirmPupil(id))
            .handleFetchedData()
    }
}
































