package com.sparkfusion.domainpupilservices.magazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portmagazine.IMagazineRepository
import com.sparkfusion.domainpupilservices.magazine.mapper.WeekDayPassEntityMapper
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.WeekDayPassModel
import com.sparkfusion.portdomainservices.pupil.portmagazine.usecase.IReadWeekStatisticsUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import java.time.LocalDate
import javax.inject.Inject

@ViewModelScoped
internal class ReadWeekStatisticsUseCase @Inject constructor(
    private val magazineRepository: IMagazineRepository,
    private val weekDayPassEntityMapper: WeekDayPassEntityMapper
) : IReadWeekStatisticsUseCase {

    override suspend fun readWeekStatistics(groupMemberId: Int): Answer<List<WeekDayPassModel>> {
        return magazineRepository.readWeekStatistics(groupMemberId, LocalDate.now())
            .suspendMap { list -> list.map { weekDayPassEntityMapper.map(it) } }
    }
}
























