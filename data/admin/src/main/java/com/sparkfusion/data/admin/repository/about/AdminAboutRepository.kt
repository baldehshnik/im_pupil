package com.sparkfusion.data.admin.repository.about

import com.sparkfusion.core.common.api.ApiResponseHandler
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.image.FileToMultipartWorker
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.admin.source.AdminAboutApiService
import com.sparkfusion.dataport.admin.portabout.IAdminAboutRepository
import kotlinx.coroutines.CoroutineDispatcher
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AdminAboutRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val adminAboutApiService: AdminAboutApiService,
    private val fileToMultipartWorker: FileToMultipartWorker
) : IAdminAboutRepository {

    override suspend fun updateAboutBlock(
        aboutId: Int?,
        description: String?,
        icon: String?,
        image: File?
    ): Answer<Unit> = safeApiCall(ioDispatcher) {
        val multipart = image?.let { fileToMultipartWorker.convert(it) }
        ApiResponseHandler(
            adminAboutApiService.updateAboutBlock(
                aboutId,
                description,
                icon,
                multipart
            )
        ).handleFetchedData()
    }
}
























