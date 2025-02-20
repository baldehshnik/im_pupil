package com.sparkfusion.data.pupil.repository

import com.sparkfusion.core.common.api.ApiListResponseHandler
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.pupil.mapper.session.SessionDataEntityMapper
import com.sparkfusion.data.pupil.source.SessionApiService
import com.sparkfusion.dataport.pupil.portsession.ISessionRepository
import com.sparkfusion.dataport.pupil.portsession.SessionEntity
import dagger.Lazy
import kotlinx.coroutines.CoroutineDispatcher
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SessionRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: Lazy<CoroutineDispatcher>,
    private val sessionApiService: Lazy<SessionApiService>,
    private val sessionDataEntityMapper: Lazy<SessionDataEntityMapper>
) : ISessionRepository {

    override suspend fun readExams(date: LocalDate): Answer<List<SessionEntity>> = safeApiCall(ioDispatcher.get()) {
        ApiListResponseHandler(sessionApiService.get().readExams(date))
            .handleFetchedData()
            .suspendMap { list ->
                list.map {
                    sessionDataEntityMapper.get().map(it)
                }
            }
    }
}



























