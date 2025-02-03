package com.sparkfusion.data.admin.repository.section

import com.sparkfusion.core.common.api.ApiListResponseHandler
import com.sparkfusion.core.common.api.ApiResponseHandler
import com.sparkfusion.core.common.api.parser.RequestBodyParser
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.image.FileToMultipartWorker
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.admin.source.SectionApiService
import com.sparkfusion.dataport.admin.portsections.ISectionsRepository
import com.sparkfusion.dataport.admin.portsections.entity.CreateSectionEntity
import com.sparkfusion.dataport.admin.portsections.entity.ReadSectionEntity
import com.sparkfusion.dataport.admin.portsections.entity.UpdateSectionEntity
import kotlinx.coroutines.CoroutineDispatcher
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SectionRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val sectionApiService: SectionApiService,
    private val fileToMultipartWorker: FileToMultipartWorker,
    private val requestBodyParser: RequestBodyParser
) : ISectionsRepository {

    override suspend fun readSections(institutionId: Int): Answer<List<ReadSectionEntity>> =
        safeApiCall(ioDispatcher) {
            ApiListResponseHandler(sectionApiService.readSections(institutionId))
                .handleFetchedData()
        }

    override suspend fun readSectionById(id: Int): Answer<ReadSectionEntity> =
        safeApiCall(ioDispatcher) {
            ApiResponseHandler(sectionApiService.readSectionById(id))
                .handleFetchedData()
        }

    override suspend fun createSection(
        createSection: CreateSectionEntity,
        image: File
    ): Answer<Unit> = safeApiCall(ioDispatcher) {
        val multipart = fileToMultipartWorker.convert(image)
        ApiResponseHandler(
            sectionApiService.createSection(
                requestBodyParser.parse(createSection),
                multipart
            )
        ).handleFetchedData()
    }

    override suspend fun deleteSectionById(id: Int): Answer<Unit> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(sectionApiService.deleteSectionById(id))
            .handleFetchedData()
    }

    override suspend fun updateSection(
        updateSection: UpdateSectionEntity,
        image: File?
    ): Answer<Unit> = safeApiCall(ioDispatcher) {
        val multipart = image?.let { fileToMultipartWorker.convert(it) }
        ApiResponseHandler(
            sectionApiService.updateSection(
                requestBodyParser.parse(updateSection),
                multipart
            )
        ).handleFetchedData()
    }
}




























