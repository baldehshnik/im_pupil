package com.sparkfusion.data.pupil.repository

import com.sparkfusion.core.common.api.ApiResponseHandler
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.pupil.mapper.event_details.ReadInstitutionEventDataEntityMapper
import com.sparkfusion.data.pupil.source.InstitutionEventApiService
import com.sparkfusion.dataport.pupil.porteventdetails.EventDetailsEntity
import com.sparkfusion.dataport.pupil.porteventdetails.IEventDetailsRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class EventDetailsRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val institutionEventApiService: InstitutionEventApiService,
    private val readInstitutionEventDataEntityMapper: ReadInstitutionEventDataEntityMapper
): IEventDetailsRepository {

    override suspend fun readEventById(id: Int): Answer<EventDetailsEntity> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(institutionEventApiService.readEventById(id))
            .handleFetchedData()
            .suspendMap { readInstitutionEventDataEntityMapper.map(it) }
    }
}






















