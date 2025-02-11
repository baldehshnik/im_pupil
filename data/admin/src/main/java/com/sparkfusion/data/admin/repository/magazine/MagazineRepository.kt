package com.sparkfusion.data.admin.repository.magazine

import com.sparkfusion.core.common.api.ApiListResponseHandler
import com.sparkfusion.core.common.api.ApiResponseHandler
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.admin.source.GroupApiService
import com.sparkfusion.data.admin.source.GroupMemberApiService
import com.sparkfusion.data.admin.source.InstitutionApiService
import com.sparkfusion.data.admin.source.schedule.LessonApiService
import com.sparkfusion.data.admin.source.schedule.PassApiService
import com.sparkfusion.data.admin.source.schedule.ScheduleApiService
import com.sparkfusion.data.commonentity.CommonGroupDataEntity
import com.sparkfusion.data.commonentity.institution.CommonFacultyDataEntity
import com.sparkfusion.dataport.admin.portmagazine.IMagazineRepository
import com.sparkfusion.dataport.admin.portmagazine.entity.ReadGroupMemberEntity
import com.sparkfusion.dataport.admin.portmagazine.entity.ReadGroupMemberWithPassesEntity
import com.sparkfusion.dataport.admin.portmagazine.entity.ReadLessonEntity
import com.sparkfusion.dataport.admin.portmagazine.entity.ReadLessonWithPassStatusEntity
import com.sparkfusion.dataport.admin.portmagazine.entity.ReadWeekDayPassEntity
import com.sparkfusion.dataport.admin.portmagazine.entity.UpdatePassStatusEntity
import com.sparkfusion.dataport.admin.portmagazine.entity.UpdatePassesStatusEntity
import kotlinx.coroutines.CoroutineDispatcher
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class MagazineRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val institutionApiService: InstitutionApiService,
    private val groupMemberApiService: GroupMemberApiService,
    private val lessonApiService: LessonApiService,
    private val scheduleApiService: ScheduleApiService,
    private val passApiService: PassApiService,
    private val groupApiService: GroupApiService
) : IMagazineRepository {

    override suspend fun readFaculties(): Answer<List<CommonFacultyDataEntity>> = safeApiCall(ioDispatcher) {
        ApiListResponseHandler(institutionApiService.readFaculties())
            .handleFetchedData()
    }

    override suspend fun readGroupByNamePart(institutionId: Int, namePart: String): Answer<List<CommonGroupDataEntity>> = safeApiCall(ioDispatcher) {
        ApiListResponseHandler(groupApiService.readGroupByNamePart(institutionId, namePart))
            .handleFetchedData()
    }

    override suspend fun readGroupMembersForMagazine(groupId: Int): Answer<List<ReadGroupMemberEntity>> =
        safeApiCall(ioDispatcher) {
            ApiListResponseHandler(groupMemberApiService.readGroupMembersForMagazine(groupId))
                .handleFetchedData()
        }

    override suspend fun readLessonsWithPassStatus(
        groupMemberId: Int,
        date: LocalDate
    ): Answer<List<ReadLessonWithPassStatusEntity>> = safeApiCall(ioDispatcher) {
        ApiListResponseHandler(scheduleApiService.readLessonsWithPassStatus(groupMemberId, date))
            .handleFetchedData()
    }

    override suspend fun readTodaySchedule(
        groupId: Int,
        currentDate: LocalDate
    ): Answer<List<ReadLessonEntity>> = safeApiCall(ioDispatcher) {
        ApiListResponseHandler(lessonApiService.readTodaySchedule(groupId, currentDate))
            .handleFetchedData()
    }

    override suspend fun readWeekStatistics(
        groupMemberId: Int,
        date: LocalDate
    ): Answer<List<ReadWeekDayPassEntity>> = safeApiCall(ioDispatcher) {
        ApiListResponseHandler(passApiService.readWeekStatistics(groupMemberId, date))
            .handleFetchedData()
    }

    override suspend fun readPassOfGroupMember(
        groupMemberId: Int,
        lessonId: Int,
        date: LocalDate
    ): Answer<ReadGroupMemberWithPassesEntity> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(lessonApiService.readPassOfGroupMember(groupMemberId, lessonId, date))
            .handleFetchedData()
    }

    override suspend fun readPasses(
        groupId: Int,
        lessonId: Int,
        date: LocalDate
    ): Answer<List<ReadGroupMemberWithPassesEntity>> = safeApiCall(ioDispatcher) {
        ApiResponseHandler(lessonApiService.readPasses(groupId, lessonId, date))
            .handleFetchedData()
    }

    override suspend fun updatePassOfGroupMember(updatePassStatusEntity: UpdatePassStatusEntity): Answer<Unit> =
        safeApiCall(ioDispatcher) {
            ApiResponseHandler(lessonApiService.updatePassOfGroupMember(updatePassStatusEntity))
                .handleFetchedData()
        }

    override suspend fun updatePassesOfGroupMember(updatePassesStatusEntity: UpdatePassesStatusEntity): Answer<Unit> =
        safeApiCall(ioDispatcher) {
            ApiResponseHandler(lessonApiService.updatePassesOfGroupMember(updatePassesStatusEntity))
                .handleFetchedData()
        }
}


























