package com.sparkfusion.domainadminservices.magazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portmagazine.IMagazineRepository
import com.sparkfusion.domainadminservices.magazine.mapper.ReadWeekDayPassEntityMapper
import com.sparkfusion.portdomainservices.admin.portmagazine.model.ReadWeekDayPassModel
import com.sparkfusion.portdomainservices.admin.portmagazine.usecase.IReadWeekStatisticsUseCase
import java.time.LocalDate
import javax.inject.Inject

internal class ReadWeekStatisticsUseCase @Inject constructor(
    private val magazineRepository: IMagazineRepository,
    private val readWeekDayPassEntityMapper: ReadWeekDayPassEntityMapper
): IReadWeekStatisticsUseCase {

    override suspend fun readWeekStatistics(groupMemberId: Int): Answer<List<ReadWeekDayPassModel>> {
        return magazineRepository.readWeekStatistics(groupMemberId, LocalDate.now())
            .suspendMap { list -> list.map { readWeekDayPassEntityMapper.map(it) } }
    }
}


















