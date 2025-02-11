package com.sparkfusion.data.admin.repository.institution

import com.sparkfusion.core.common.api.ApiResponseHandler
import com.sparkfusion.core.common.api.parser.RequestBodyParser
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.image.FileToMultipartWorker
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.admin.source.AdminInstitutionEventApiService
import com.sparkfusion.dataport.admin.portinstitutionevent.AddInstitutionEventEntity
import com.sparkfusion.dataport.admin.portinstitutionevent.IAdminInstitutionEventRepository
import com.sparkfusion.dataport.admin.portinstitutionevent.UpdateInstitutionEventEntity
import kotlinx.coroutines.CoroutineDispatcher
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AdminInstitutionEventRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val adminInstitutionEventApiService: AdminInstitutionEventApiService,
    private val fileToMultipartWorker: FileToMultipartWorker,
    private val requestBodyParser: RequestBodyParser
) : IAdminInstitutionEventRepository {

    override suspend fun deleteEventById(id: Int): Answer<Unit> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(adminInstitutionEventApiService.deleteEventById(id))
            .handleFetchedData()
    }

    override suspend fun addEvent(entity: AddInstitutionEventEntity, image: File): Answer<Unit> =
        safeApiCall(ioDispatcher) {
            ApiResponseHandler(
                adminInstitutionEventApiService.addEvent(
                    requestBodyParser.parse(entity),
                    fileToMultipartWorker.convert(image)
                )
            ).handleFetchedData()
        }

    override suspend fun updateEvent(
        entity: UpdateInstitutionEventEntity,
        image: File?
    ): Answer<Unit> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(
            adminInstitutionEventApiService.updateEvent(
                requestBodyParser.parse(entity),
                image?.let { fileToMultipartWorker.convert(it) }
            )
        ).handleFetchedData()
    }
}























