package com.sparkfusion.domainpupilservices.statistics.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portstatistics.IStatisticsRepository
import com.sparkfusion.domainpupilservices.statistics.mapper.PassEntityMapper
import com.sparkfusion.portdomainservices.pupil.portstatistics.model.PassModel
import com.sparkfusion.portdomainservices.pupil.portstatistics.usecase.IReadGroupMemberPassesPerMonthUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import java.time.LocalDate
import javax.inject.Inject

@ViewModelScoped
internal class ReadGroupMemberPassesPerMonthUseCase @Inject constructor(
    private val statisticsRepository: IStatisticsRepository,
    private val passEntityMapper: PassEntityMapper
) : IReadGroupMemberPassesPerMonthUseCase {

    override suspend fun readGroupMemberPassesPerMonth(
        groupMemberId: Int
    ): Answer<List<PassModel>> {
        return statisticsRepository.readGroupMemberPassesPerMonth(groupMemberId, LocalDate.now())
            .suspendMap { list -> list.map { passEntityMapper.map(it) } }

    }
}




















