package com.sparkfusion.domain.admin.port.portservices

import com.sparkfusion.core.common.result.Answer

interface IReadNewsUseCase {
    suspend fun loadNews(): Answer<List<NewsEntity>>
}