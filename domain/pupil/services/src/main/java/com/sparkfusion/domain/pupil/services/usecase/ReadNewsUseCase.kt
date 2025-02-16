package com.sparkfusion.domain.pupil.services.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataPort.common.portnews.INewsRepository
import com.sparkfusion.domain.pupil.port.portservices.model.NewsModel
import com.sparkfusion.domain.pupil.port.portservices.usecase.IReadNewsUseCase
import com.sparkfusion.domain.pupil.services.mapper.NewsDataEntityToNewsEntityMapper
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ReadNewsUseCase @Inject constructor(
    private val newsRepository: INewsRepository,
    private val newsEntityMapper: NewsDataEntityToNewsEntityMapper
): IReadNewsUseCase {

    override suspend fun loadNews(): Answer<List<NewsModel>> {
        return newsRepository.loadNews()
            .suspendMap { list -> list.map { newsEntityMapper.map(it) } }
    }
}





















