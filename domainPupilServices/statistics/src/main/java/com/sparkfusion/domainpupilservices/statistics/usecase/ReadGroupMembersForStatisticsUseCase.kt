package com.sparkfusion.domainpupilservices.statistics.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portstatistics.IStatisticsRepository
import com.sparkfusion.domainpupilservices.statistics.mapper.GroupMemberEntityMapper
import com.sparkfusion.portdomainservices.pupil.portstatistics.model.GroupMemberModel
import com.sparkfusion.portdomainservices.pupil.portstatistics.usecase.IReadGroupMembersForStatisticsUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ReadGroupMembersForStatisticsUseCase @Inject constructor(
    private val statisticsRepository: IStatisticsRepository,
    private val groupMemberEntityMapper: GroupMemberEntityMapper
) : IReadGroupMembersForStatisticsUseCase {

    override suspend fun readGroupMembersForStatistics(): Answer<List<GroupMemberModel>> {
        return statisticsRepository.readGroupMembersForStatistics()
            .suspendMap { list -> list.map { groupMemberEntityMapper.map(it) } }
    }
}






















