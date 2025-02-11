package com.sparkfusion.domainadminservices.statistics.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portstatistics.IStatisticsRepository
import com.sparkfusion.domainadminservices.statistics.mapper.ReadFullPassWithGroupMemberEntityMapper
import com.sparkfusion.portdomainservices.admin.portstatistics.model.ReadFullPassWithGroupMemberModel
import com.sparkfusion.portdomainservices.admin.portstatistics.usecase.IReadPassesOfGroupPerMonthUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import java.time.LocalDate
import javax.inject.Inject

@ViewModelScoped
internal class ReadPassesOfGroupPerMonthUseCase @Inject constructor(
    private val statisticsRepository: IStatisticsRepository,
    private val readFullPassWithGroupMemberEntityMapper: ReadFullPassWithGroupMemberEntityMapper
) : IReadPassesOfGroupPerMonthUseCase {

    override suspend fun readPassesOfGroupPerMonth(
        groupId: Int
    ): Answer<List<ReadFullPassWithGroupMemberModel>> {
        return statisticsRepository.readPassesOfGroupPerMonth(
            groupId, LocalDate.now()
        ).suspendMap { list ->
            list.map { readFullPassWithGroupMemberEntityMapper.map(it) }
        }
    }
}






















