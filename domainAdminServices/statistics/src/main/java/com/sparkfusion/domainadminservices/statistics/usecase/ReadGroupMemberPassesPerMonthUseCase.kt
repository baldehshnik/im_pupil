package com.sparkfusion.domainadminservices.statistics.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portstatistics.IStatisticsRepository
import com.sparkfusion.domainadminservices.statistics.mapper.ReadFullPassEntityMapper
import com.sparkfusion.portdomainservices.admin.portstatistics.model.ReadFullPassModel
import com.sparkfusion.portdomainservices.admin.portstatistics.usecase.IReadGroupMemberPassesPerMonthUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import java.time.LocalDate
import javax.inject.Inject

@ViewModelScoped
internal class ReadGroupMemberPassesPerMonthUseCase @Inject constructor(
    private val statisticsRepository: IStatisticsRepository,
    private val readFullPassEntityMapper: ReadFullPassEntityMapper
) : IReadGroupMemberPassesPerMonthUseCase {

    override suspend fun readGroupMemberPassesPerMonth(groupMemberId: Int): Answer<List<ReadFullPassModel>> {
        return statisticsRepository.readGroupMemberPassesPerMonth(
            groupMemberId, LocalDate.now()
        ).suspendMap { list ->
            list.map { readFullPassEntityMapper.map(it) }
        }
    }
}























