package com.sparkfusion.data.pupil.repository

import com.sparkfusion.core.common.api.ApiListResponseHandler
import com.sparkfusion.core.common.api.ApiResponseHandler
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.pupil.mapper.magazine.AccountDataEntityMapper
import com.sparkfusion.data.pupil.mapper.magazine.GroupMemberDataEntityMapper
import com.sparkfusion.data.pupil.mapper.magazine.GroupMemberWithPassDataEntityMapper
import com.sparkfusion.data.pupil.mapper.magazine.LessonDataEntityMapper
import com.sparkfusion.data.pupil.mapper.magazine.LessonWithPassStatusDataEntityMapper
import com.sparkfusion.data.pupil.mapper.magazine.UpdatePassStatusEntityMapper
import com.sparkfusion.data.pupil.mapper.magazine.UpdatePassesStatusEntityMapper
import com.sparkfusion.data.pupil.mapper.magazine.WeekDayPassDataEntityMapper
import com.sparkfusion.data.pupil.source.AccountApiService
import com.sparkfusion.data.pupil.source.GroupMemberApiService
import com.sparkfusion.data.pupil.source.MagazineApiService
import com.sparkfusion.data.pupil.source.ScheduleApiService
import com.sparkfusion.dataport.pupil.portmagazine.AccountEntity
import com.sparkfusion.dataport.pupil.portmagazine.GroupMemberEntity
import com.sparkfusion.dataport.pupil.portmagazine.GroupMemberWithPassEntity
import com.sparkfusion.dataport.pupil.portmagazine.IMagazineRepository
import com.sparkfusion.dataport.pupil.portmagazine.LessonEntity
import com.sparkfusion.dataport.pupil.portmagazine.LessonWithPassStatusEntity
import com.sparkfusion.dataport.pupil.portmagazine.UpdatePassStatusEntity
import com.sparkfusion.dataport.pupil.portmagazine.UpdatePassesStatusEntity
import com.sparkfusion.dataport.pupil.portmagazine.WeekDayPassEntity
import dagger.Lazy
import kotlinx.coroutines.CoroutineDispatcher
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class MagazineRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: Lazy<CoroutineDispatcher>,
    private val accountApiService: Lazy<AccountApiService>,
    private val scheduleApiService: Lazy<ScheduleApiService>,
    private val magazineApiService: Lazy<MagazineApiService>,
    private val groupMemberApiService: Lazy<GroupMemberApiService>,
    private val accountDataEntityMapper: Lazy<AccountDataEntityMapper>,
    private val groupMemberWithPassDataEntityMapper: Lazy<GroupMemberWithPassDataEntityMapper>,
    private val weekDayPassDataEntityMapper: Lazy<WeekDayPassDataEntityMapper>,
    private val updatePassStatusEntityMapper: Lazy<UpdatePassStatusEntityMapper>,
    private val updatePassesStatusEntityMapper: Lazy<UpdatePassesStatusEntityMapper>,
    private val lessonDataEntityMapper: Lazy<LessonDataEntityMapper>,
    private val groupMemberDataEntityMapper: Lazy<GroupMemberDataEntityMapper>,
    private val lessonWithPassStatusDataEntityMapper: Lazy<LessonWithPassStatusDataEntityMapper>
) : IMagazineRepository {

    override suspend fun readPupilAccount(): Answer<AccountEntity?> =
        safeApiCall(ioDispatcher.get()) {
            ApiResponseHandler(accountApiService.get().readPupilAccount())
                .handleFetchedData()
                .suspendMap { model -> model?.let { accountDataEntityMapper.get().map(it) } }
        }

    override suspend fun readGroupMembers(): Answer<List<GroupMemberEntity>> =
        safeApiCall(ioDispatcher.get()) {
            ApiListResponseHandler(groupMemberApiService.get().readGroupMembers())
                .handleFetchedData()
                .suspendMap { list -> list.map { groupMemberDataEntityMapper.get().map(it) } }
        }

    override suspend fun readTodaySchedule(currentDate: LocalDate): Answer<List<LessonEntity>> =
        safeApiCall(ioDispatcher.get()) {
            ApiListResponseHandler(scheduleApiService.get().readTodaySchedule(currentDate))
                .handleFetchedData()
                .suspendMap { list -> list.map { lessonDataEntityMapper.get().map(it) } }
        }

    override suspend fun readScheduleWithPasses(
        groupMemberId: Int,
        date: LocalDate
    ): Answer<List<LessonWithPassStatusEntity>> = safeApiCall(ioDispatcher.get()) {
        ApiListResponseHandler(scheduleApiService.get().readScheduleWithPasses(groupMemberId, date))
            .handleFetchedData()
            .suspendMap { list ->
                list.map {
                    lessonWithPassStatusDataEntityMapper.get().map(it)
                }
            }
    }

    override suspend fun readPasses(
        lessonId: Int,
        date: LocalDate
    ): Answer<List<GroupMemberWithPassEntity>> = safeApiCall(ioDispatcher.get()) {
        ApiListResponseHandler(magazineApiService.get().readPasses(lessonId, date))
            .handleFetchedData()
            .suspendMap { list ->
                list.map {
                    groupMemberWithPassDataEntityMapper.get().map(it)
                }
            }
    }

    override suspend fun readPassOfGroupMember(
        groupMemberId: Int,
        lessonId: Int,
        date: LocalDate
    ): Answer<GroupMemberWithPassEntity> = safeApiCall(ioDispatcher.get()) {
        ApiResponseHandler(
            magazineApiService.get().readPassOfGroupMember(groupMemberId, lessonId, date)
        )
            .handleFetchedData()
            .suspendMap { groupMemberWithPassDataEntityMapper.get().map(it) }
    }

    override suspend fun readWeekStatistics(
        groupMemberId: Int,
        date: LocalDate
    ): Answer<List<WeekDayPassEntity>> = safeApiCall(ioDispatcher.get()) {
        ApiListResponseHandler(magazineApiService.get().readWeekStatistics(groupMemberId, date))
            .handleFetchedData()
            .suspendMap { list ->
                list.map {
                    weekDayPassDataEntityMapper.get().map(it)
                }
            }
    }

    override suspend fun updatePassOfGroupMember(updatePassStatus: UpdatePassStatusEntity): Answer<Unit> =
        safeApiCall(ioDispatcher.get()) {
            ApiResponseHandler(
                magazineApiService.get().updatePassOfGroupMember(
                    updatePassStatusEntityMapper.get().map(updatePassStatus)
                )
            ).handleFetchedData()
        }

    override suspend fun updatePasses(updatePassesStatus: UpdatePassesStatusEntity): Answer<Unit> =
        safeApiCall(ioDispatcher.get()) {
            ApiResponseHandler(
                magazineApiService.get().updatePasses(
                    updatePassesStatusEntityMapper.get().map(
                        updatePassesStatus
                    )
                )
            ).handleFetchedData()
        }
}























