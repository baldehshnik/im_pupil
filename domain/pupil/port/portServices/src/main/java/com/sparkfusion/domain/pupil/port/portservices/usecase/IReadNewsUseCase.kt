package com.sparkfusion.domain.pupil.port.portservices.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.domain.pupil.port.portservices.model.NewsModel

interface IReadNewsUseCase {

    suspend fun loadNews(): Answer<List<NewsModel>>
}