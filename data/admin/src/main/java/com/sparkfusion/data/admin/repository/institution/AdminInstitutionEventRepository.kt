package com.sparkfusion.data.admin.repository.institution

import com.sparkfusion.core.common.api.ApiResponseHandler
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.admin.source.AdminInstitutionEventApiService
import com.sparkfusion.dataport.admin.portinstitutionevent.IAdminInstitutionEventRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdminInstitutionEventRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val adminInstitutionEventApiService: AdminInstitutionEventApiService
): IAdminInstitutionEventRepository {

    override suspend fun deleteEventById(id: Int): Answer<Unit> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(adminInstitutionEventApiService.deleteEventById(id))
            .handleFetchedData()
    }
}























