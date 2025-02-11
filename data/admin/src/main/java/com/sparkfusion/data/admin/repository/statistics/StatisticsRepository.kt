package com.sparkfusion.data.admin.repository.statistics

import com.sparkfusion.core.common.api.ApiListResponseHandler
import com.sparkfusion.core.common.api.safeApiCall
import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.admin.source.GroupApiService
import com.sparkfusion.data.admin.source.GroupMemberApiService
import com.sparkfusion.data.admin.source.schedule.PassApiService
import com.sparkfusion.data.commonentity.CommonGroupDataEntity
import com.sparkfusion.dataport.admin.portstatistics.IStatisticsRepository
import com.sparkfusion.dataport.admin.portstatistics.entity.ReadFullPassEntity
import com.sparkfusion.dataport.admin.portstatistics.entity.ReadFullPassWithGroupMemberEntity
import com.sparkfusion.dataport.admin.portstatistics.entity.ReadGroupMemberEntity
import kotlinx.coroutines.CoroutineDispatcher
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class StatisticsRepository @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val groupMemberApiService: GroupMemberApiService,
    private val passApiService: PassApiService,
    private val groupApiService: GroupApiService
) : IStatisticsRepository {

    override suspend fun readGroupMembersForStatistics(groupId: Int): Answer<List<ReadGroupMemberEntity>> =
        safeApiCall(ioDispatcher) {
            ApiListResponseHandler(groupMemberApiService.readGroupMembersForStatistics(groupId))
                .handleFetchedData()
        }

    override suspend fun readGroupByNamePart(
        institutionId: Int,
        namePart: String
    ): Answer<List<CommonGroupDataEntity>> = safeApiCall(ioDispatcher) {
        ApiListResponseHandler(groupApiService.readGroupByNamePart(institutionId, namePart))
            .handleFetchedData()
    }

    override suspend fun readGroupMemberPassesPerSemester(groupMemberId: Int): Answer<List<ReadFullPassEntity>> =
        safeApiCall(ioDispatcher) {
            ApiListResponseHandler(passApiService.readGroupMemberPassesPerSemester(groupMemberId))
                .handleFetchedData()
        }

    override suspend fun readGroupMemberPassesPerMonth(
        groupMemberId: Int,
        date: LocalDate
    ): Answer<List<ReadFullPassEntity>> = safeApiCall(ioDispatcher) {
        ApiListResponseHandler(passApiService.readGroupMemberPassesPerMonth(groupMemberId, date))
            .handleFetchedData()
    }

    override suspend fun readPassesOfGroupPerMonth(
        groupId: Int,
        date: LocalDate
    ): Answer<List<ReadFullPassWithGroupMemberEntity>> = safeApiCall(ioDispatcher) {
        ApiListResponseHandler(passApiService.readPassesOfGroupPerMonth(groupId, date))
            .handleFetchedData()
    }
}

























