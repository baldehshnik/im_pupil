package com.sparkfusion.data.pupil.repository

import com.sparkfusion.core.common.api.ApiListResponseHandler
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.pupil.mapper.home.ReadInstitutionEventDataEntityMapper
import com.sparkfusion.data.pupil.source.InstitutionEventApiService
import com.sparkfusion.dataport.pupil.porthome.IHomeRepository
import com.sparkfusion.dataport.pupil.porthome.ReadInstitutionEventEntity
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class HomeRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val institutionEventApiService: InstitutionEventApiService,
    private val readInstitutionEventDataEntityMapper: ReadInstitutionEventDataEntityMapper
): IHomeRepository {

    override suspend fun readEvents(): Answer<List<ReadInstitutionEventEntity>> = safeApiCall(ioDispatcher) {
        ApiListResponseHandler(institutionEventApiService.readEvents())
            .handleFetchedData()
            .suspendMap { list ->
                list.map { readInstitutionEventDataEntityMapper.map(it) }
            }
    }
}

























