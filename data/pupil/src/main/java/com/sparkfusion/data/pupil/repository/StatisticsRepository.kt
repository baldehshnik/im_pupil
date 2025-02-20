package com.sparkfusion.data.pupil.repository

import com.sparkfusion.core.common.api.ApiListResponseHandler
import com.sparkfusion.core.common.api.ApiResponseHandler
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.pupil.mapper.statistics.GroupMemberDataEntityMapper
import com.sparkfusion.data.pupil.mapper.statistics.PassDataEntityMapper
import com.sparkfusion.data.pupil.mapper.statistics.PassWithGroupMemberDataEntityMapper
import com.sparkfusion.data.pupil.source.GroupMemberApiService
import com.sparkfusion.data.pupil.source.StatisticsApiService
import com.sparkfusion.dataport.pupil.portstatistics.IStatisticsRepository
import com.sparkfusion.dataport.pupil.portstatistics.entity.GroupMemberEntity
import com.sparkfusion.dataport.pupil.portstatistics.entity.PassEntity
import com.sparkfusion.dataport.pupil.portstatistics.entity.PassWithGroupMemberEntity
import dagger.Lazy
import kotlinx.coroutines.CoroutineDispatcher
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class StatisticsRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: Lazy<CoroutineDispatcher>,
    private val groupMemberApiService: Lazy<GroupMemberApiService>,
    private val statisticsApiService: Lazy<StatisticsApiService>,
    private val groupMemberDataEntityMapper: Lazy<GroupMemberDataEntityMapper>,
    private val passWithGroupMemberDataEntityMapper: Lazy<PassWithGroupMemberDataEntityMapper>,
    private val passDataEntityMapper: Lazy<PassDataEntityMapper>
) : IStatisticsRepository {

    override suspend fun readGroupMembersForStatistics(): Answer<List<GroupMemberEntity>> =
        safeApiCall(ioDispatcher.get()) {
            ApiListResponseHandler(groupMemberApiService.get().readGroupMembers())
                .handleFetchedData()
                .suspendMap { list ->
                    list.map {
                        groupMemberDataEntityMapper.get().map(it)
                    }
                }
        }

    override suspend fun readGroupMemberById(id: Int): Answer<GroupMemberEntity> =
        safeApiCall(ioDispatcher.get()) {
            ApiResponseHandler(groupMemberApiService.get().readGroupMemberById(id))
                .handleFetchedData()
                .suspendMap { groupMemberDataEntityMapper.get().map(it) }
        }

    override suspend fun readPassesOfGroupPerMonth(
        date: LocalDate
    ): Answer<List<PassWithGroupMemberEntity>> = safeApiCall(ioDispatcher.get()) {
        ApiListResponseHandler(statisticsApiService.get().readPassesOfGroupPerMonth(date))
            .handleFetchedData()
            .suspendMap { list ->
                list.map {
                    passWithGroupMemberDataEntityMapper.get().map(it)
                }
            }
    }

    override suspend fun readGroupMemberPassesPerMonth(
        groupMemberId: Int,
        date: LocalDate
    ): Answer<List<PassEntity>> = safeApiCall(ioDispatcher.get()) {
        ApiListResponseHandler(
            statisticsApiService.get().readGroupMemberPassesPerMonth(groupMemberId, date)
        ).handleFetchedData()
            .suspendMap { list ->
                list.map {
                    passDataEntityMapper.get().map(it)
                }
            }
    }

    override suspend fun readGroupMemberPassesPerSemester(groupMemberId: Int): Answer<List<PassEntity>> =
        safeApiCall(ioDispatcher.get()) {
            ApiListResponseHandler(
                statisticsApiService.get().readGroupMemberPassesPerSemester(groupMemberId)
            ).handleFetchedData()
                .suspendMap { list ->
                    list.map {
                        passDataEntityMapper.get().map(it)
                    }
                }
        }
}





























