package com.sparkfusion.domainadminservices.statistics.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portstatistics.IStatisticsRepository
import com.sparkfusion.domainadminservices.statistics.mapper.ReadFullPassEntityMapper
import com.sparkfusion.portdomainservices.admin.portstatistics.model.ReadFullPassModel
import com.sparkfusion.portdomainservices.admin.portstatistics.usecase.IReadGroupMemberPassesPerSemesterUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ReadGroupMemberPassesPerSemesterUseCase @Inject constructor(
    private val statisticsRepository: IStatisticsRepository,
    private val readFullPassEntityMapper: ReadFullPassEntityMapper
): IReadGroupMemberPassesPerSemesterUseCase {

    override suspend fun readGroupMemberPassesPerSemester(groupMemberId: Int): Answer<List<ReadFullPassModel>> {
        return statisticsRepository.readGroupMemberPassesPerSemester(groupMemberId)
            .suspendMap { list ->
                list.map {
                    readFullPassEntityMapper.map(it)
                }
            }
    }
}























