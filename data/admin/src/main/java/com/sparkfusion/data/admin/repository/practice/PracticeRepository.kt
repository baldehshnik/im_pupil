package com.sparkfusion.data.admin.repository.practice

import com.sparkfusion.core.common.api.ApiListResponseHandler
import com.sparkfusion.core.common.api.ApiResponseHandler
import com.sparkfusion.core.common.api.parser.RequestBodyParser
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.image.FileToMultipartWorker
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.admin.source.PracticeApiService
import com.sparkfusion.dataport.admin.portpractice.IPracticeRepository
import com.sparkfusion.dataport.admin.portpractice.entity.CreatePracticeEntity
import com.sparkfusion.dataport.admin.portpractice.entity.ReadListPracticeEntity
import com.sparkfusion.dataport.admin.portpractice.entity.ReadPracticeEntity
import com.sparkfusion.dataport.admin.portpractice.entity.UpdatePracticeEntity
import kotlinx.coroutines.CoroutineDispatcher
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PracticeRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val practiceApiService: PracticeApiService,
    private val fileToMultipartWorker: FileToMultipartWorker,
    private val requestBodyParser: RequestBodyParser
) : IPracticeRepository {

    override suspend fun readPracticeById(id: Int): Answer<ReadPracticeEntity> =
        safeApiCall(ioDispatcher) {
            ApiResponseHandler(practiceApiService.readPracticeById(id))
                .handleFetchedData()
        }

    override suspend fun readPractices(institutionId: Int): Answer<List<ReadListPracticeEntity>> =
        safeApiCall(ioDispatcher) {
            ApiListResponseHandler(practiceApiService.readPractices(institutionId))
                .handleFetchedData()
        }

    override suspend fun deletePracticeById(id: Int): Answer<Unit> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(practiceApiService.deletePracticeById(id))
            .handleFetchedData()
    }

    override suspend fun createPractice(practice: CreatePracticeEntity, image: File): Answer<Unit> =
        safeApiCall(ioDispatcher) {
            ApiResponseHandler(
                practiceApiService.createPractice(
                    requestBodyParser.parse(practice),
                    fileToMultipartWorker.convert(image)
                )
            ).handleFetchedData()
        }

    override suspend fun updatePractice(
        practice: UpdatePracticeEntity,
        image: File?
    ): Answer<Unit> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(
            practiceApiService.updatePractice(
                requestBodyParser.parse(practice),
                image?.let { fileToMultipartWorker.convert(it) }
            )
        ).handleFetchedData()
    }
}



























