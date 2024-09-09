package com.sparkfusion.domain.admin.services.usecase

import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.dataport.admin.portservices.IAdminServicesRepository
import com.sparkfusion.domain.admin.port.portservices.IReadNewsUseCase
import com.sparkfusion.domain.admin.port.portservices.NewsEntity
import com.sparkfusion.domain.admin.services.mapper.NewsDataEntityToNewsEntityMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ReadNewsUseCase @Inject constructor(
    private val repository: IAdminServicesRepository,
    private val mapper: NewsDataEntityToNewsEntityMapper,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : IReadNewsUseCase {

    override suspend fun loadNews(): List<NewsEntity> = withContext(ioDispatcher) {
        return@withContext repository.loadNews().map { mapper.map(it) }
    }
}