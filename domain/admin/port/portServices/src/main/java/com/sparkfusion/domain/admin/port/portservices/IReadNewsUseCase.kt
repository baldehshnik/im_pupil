package com.sparkfusion.domain.admin.port.portservices

interface IReadNewsUseCase {
    suspend fun loadNews(): List<NewsEntity>
}