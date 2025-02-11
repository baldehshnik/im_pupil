package com.sparkfusion.data.admin.repository.schedule

import com.sparkfusion.core.common.api.ApiListResponseHandler
import com.sparkfusion.core.common.api.ApiResponseHandler
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.admin.source.GroupApiService
import com.sparkfusion.data.admin.source.InstitutionApiService
import com.sparkfusion.data.admin.source.schedule.LessonApiService
import com.sparkfusion.data.admin.source.schedule.ScheduleApiService
import com.sparkfusion.data.commonentity.CommonGroupDataEntity
import com.sparkfusion.data.commonentity.institution.CommonFacultyDataEntity
import com.sparkfusion.data.commonentity.schedule.CommonScheduleDataEntity
import com.sparkfusion.dataport.admin.portschedule.IScheduleRepository
import com.sparkfusion.dataport.admin.portschedule.entity.AddScheduleEntity
import com.sparkfusion.dataport.admin.portschedule.entity.ReadLessonEntity
import com.sparkfusion.dataport.admin.portschedule.entity.ReadScheduleWithLessonsEntity
import com.sparkfusion.dataport.admin.portschedule.entity.UpdateScheduleEntity
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ScheduleRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val scheduleApiService: ScheduleApiService,
    private val lessonApiService: LessonApiService,
    private val institutionApiService: InstitutionApiService,
    private val groupApiService: GroupApiService
): IScheduleRepository {

    override suspend fun readFaculties(): Answer<List<CommonFacultyDataEntity>> = safeApiCall(ioDispatcher) {
        ApiListResponseHandler(institutionApiService.readFaculties())
            .handleFetchedData()
    }

    override suspend fun readLessonsByScheduleId(scheduleId: Int): Answer<List<ReadLessonEntity>> = safeApiCall(ioDispatcher) {
        ApiListResponseHandler(lessonApiService.readLessonsByScheduleId(scheduleId))
            .handleFetchedData()
    }

    override suspend fun readScheduleByGroupId(groupId: Int): Answer<List<CommonScheduleDataEntity>> = safeApiCall(ioDispatcher) {
        ApiListResponseHandler(scheduleApiService.readScheduleByGroupId(groupId))
            .handleFetchedData()
    }

    override suspend fun readScheduleWithLessons(id: Int): Answer<ReadScheduleWithLessonsEntity> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(scheduleApiService.readScheduleWithLessons(id))
            .handleFetchedData()
    }

    override suspend fun createSchedule(schedule: AddScheduleEntity): Answer<Unit> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(scheduleApiService.createSchedule(schedule))
            .handleFetchedData()
    }

    override suspend fun updateSchedule(schedule: UpdateScheduleEntity): Answer<Unit> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(scheduleApiService.updateSchedule(schedule))
            .handleFetchedData()
    }

    override suspend fun makeScheduleAsACurrent(scheduleId: Int): Answer<Unit> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(scheduleApiService.makeScheduleAsACurrent(scheduleId))
            .handleFetchedData()
    }

    override suspend fun clearScheduleStatus(scheduleId: Int): Answer<Unit> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(scheduleApiService.clearScheduleStatus(scheduleId))
            .handleFetchedData()
    }

    override suspend fun readGroupByNamePart(
        institutionId: Int,
        namePart: String
    ): Answer<List<CommonGroupDataEntity>> = safeApiCall(ioDispatcher) {
        ApiListResponseHandler(groupApiService.readGroupByNamePart(institutionId, namePart))
            .handleFetchedData()
    }
}

























