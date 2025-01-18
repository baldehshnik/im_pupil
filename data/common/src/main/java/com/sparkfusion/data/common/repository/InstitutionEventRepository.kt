package com.sparkfusion.data.common.repository

import com.sparkfusion.core.common.api.ApiListResponseHandler
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.common.mapper.InstitutionEventDataEntityMapper
import com.sparkfusion.data.common.service.InstitutionEventApiService
import com.sparkfusion.dataPort.common.portinstitutionevent.IInstitutionEventRepository
import com.sparkfusion.dataPort.common.portinstitutionevent.InstitutionEventEntity
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InstitutionEventRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val institutionEventApiService: InstitutionEventApiService,
    private val institutionEventDataEntityMapper: InstitutionEventDataEntityMapper
) : IInstitutionEventRepository {

    override suspend fun readInstitutionEvents(institutionId: Int): Answer<List<InstitutionEventEntity>> =
        safeApiCall(ioDispatcher) {
            ApiListResponseHandler(institutionEventApiService.readInstitutionEvents(institutionId))
                .handleFetchedData()
                .suspendMap {
                    it.map { model -> institutionEventDataEntityMapper.map(model) }
                }
        }
}














