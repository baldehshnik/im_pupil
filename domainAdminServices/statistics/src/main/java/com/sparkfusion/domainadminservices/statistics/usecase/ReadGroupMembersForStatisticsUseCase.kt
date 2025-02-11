package com.sparkfusion.domainadminservices.statistics.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portstatistics.IStatisticsRepository
import com.sparkfusion.domainadminservices.statistics.mapper.ReadGroupMemberEntityMapper
import com.sparkfusion.portdomainservices.admin.portstatistics.model.ReadGroupMemberModel
import com.sparkfusion.portdomainservices.admin.portstatistics.usecase.IReadGroupMembersForStatisticsUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ReadGroupMembersForStatisticsUseCase @Inject constructor(
    private val statisticsRepository: IStatisticsRepository,
    private val readGroupMemberEntityMapper: ReadGroupMemberEntityMapper
): IReadGroupMembersForStatisticsUseCase {

    override suspend fun readGroupMembersForStatistics(groupId: Int): Answer<List<ReadGroupMemberModel>> {
        return statisticsRepository.readGroupMembersForStatistics(groupId)
            .suspendMap { list ->
                list.map { readGroupMemberEntityMapper.map(it) }
            }
    }
}




















