package com.sparkfusion.data.pupil.repository

import com.sparkfusion.core.common.api.ApiListResponseHandler
import com.sparkfusion.core.common.api.ApiResponseHandler
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.pupil.mapper.schedule.ScheduleDataEntityMapper
import com.sparkfusion.data.pupil.mapper.schedule.ScheduleWithLessonsDataEntityMapper
import com.sparkfusion.data.pupil.source.ScheduleApiService
import com.sparkfusion.dataport.pupil.portschedule.IScheduleRepository
import com.sparkfusion.dataport.pupil.portschedule.ScheduleEntity
import com.sparkfusion.dataport.pupil.portschedule.ScheduleWithLessonsEntity
import dagger.Lazy
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ScheduleRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: Lazy<CoroutineDispatcher>,
    private val scheduleApiService: Lazy<ScheduleApiService>,
    private val scheduleDataEntityMapper: Lazy<ScheduleDataEntityMapper>,
    private val scheduleWithLessonsDataEntityMapper: Lazy<ScheduleWithLessonsDataEntityMapper>
) : IScheduleRepository {

    override suspend fun readSchedules(): Answer<List<ScheduleEntity>> = safeApiCall(ioDispatcher.get()) {
        ApiListResponseHandler(scheduleApiService.get().readSchedules())
            .handleFetchedData()
            .suspendMap { list ->
                list.map {
                    scheduleDataEntityMapper.get().map(it)
                }
            }
    }

    override suspend fun readScheduleWithLessons(id: Int): Answer<ScheduleWithLessonsEntity> =
        safeApiCall(ioDispatcher.get()) {
            ApiResponseHandler(scheduleApiService.get().readScheduleWithLessons(id))
                .handleFetchedData()
                .suspendMap { scheduleWithLessonsDataEntityMapper.get().map(it) }
        }
}




















