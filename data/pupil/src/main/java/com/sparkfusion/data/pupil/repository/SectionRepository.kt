package com.sparkfusion.data.pupil.repository

import com.sparkfusion.core.common.api.ApiListResponseHandler
import com.sparkfusion.core.common.api.ApiResponseHandler
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.pupil.mapper.section.SectionDataEntityMapper
import com.sparkfusion.data.pupil.source.SectionApiService
import com.sparkfusion.dataport.pupil.portsection.ISectionRepository
import com.sparkfusion.dataport.pupil.portsection.SectionEntity
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SectionRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val sectionApiService: SectionApiService,
    private val sectionDataEntityMapper: SectionDataEntityMapper
) : ISectionRepository {

    override suspend fun readSections(): Answer<List<SectionEntity>> = safeApiCall(ioDispatcher) {
        ApiListResponseHandler(sectionApiService.readSections())
            .handleFetchedData()
            .suspendMap { list ->
                list.map {
                    sectionDataEntityMapper.map(it)
                }
            }
    }

    override suspend fun readSectionById(id: Int): Answer<SectionEntity> =
        safeApiCall(ioDispatcher) {
            ApiResponseHandler(sectionApiService.readSectionById(id))
                .handleFetchedData()
                .suspendMap { sectionDataEntityMapper.map(it) }
        }
}





















