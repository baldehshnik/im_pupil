package com.sparkfusion.domainpupilservices.statistics.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portstatistics.IStatisticsRepository
import com.sparkfusion.domainpupilservices.statistics.mapper.PassWithGroupMemberEntityMapper
import com.sparkfusion.portdomainservices.pupil.portstatistics.model.PassWithGroupMemberModel
import com.sparkfusion.portdomainservices.pupil.portstatistics.usecase.IReadPassesOfGroupPerMonthUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import java.time.LocalDate
import javax.inject.Inject

@ViewModelScoped
internal class ReadPassesOfGroupPerMonthUseCase @Inject constructor(
    private val statisticsRepository: IStatisticsRepository,
    private val passWithGroupMemberEntityMapper: PassWithGroupMemberEntityMapper
) : IReadPassesOfGroupPerMonthUseCase {

    override suspend fun readPassesOfGroupPerMonth(): Answer<List<PassWithGroupMemberModel>> {
        return statisticsRepository.readPassesOfGroupPerMonth(LocalDate.now())
            .suspendMap { list -> list.map { passWithGroupMemberEntityMapper.map(it) } }
    }
}





















