package com.sparkfusion.data.admin.repository.institution

import com.sparkfusion.core.common.api.ApiResponseHandler
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.admin.source.InstitutionApiService
import com.sparkfusion.dataPort.admin.portinstitution.IAdminInstitutionRepository
import com.sparkfusion.dataPort.admin.portinstitution.InstitutionEntity
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdminInstitutionRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val institutionApiService: InstitutionApiService
): IAdminInstitutionRepository {

    override suspend fun readInstitutionOfAdmin(): Answer<InstitutionEntity> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(institutionApiService.readInstitutionOfAdmin())
            .handleFetchedData()
    }
}


















