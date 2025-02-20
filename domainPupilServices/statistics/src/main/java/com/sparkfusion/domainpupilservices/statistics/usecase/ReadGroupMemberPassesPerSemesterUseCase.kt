package com.sparkfusion.domainpupilservices.statistics.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portstatistics.IStatisticsRepository
import com.sparkfusion.domainpupilservices.statistics.mapper.PassEntityMapper
import com.sparkfusion.portdomainservices.pupil.portstatistics.model.PassModel
import com.sparkfusion.portdomainservices.pupil.portstatistics.usecase.IReadGroupMemberPassesPerSemesterUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ReadGroupMemberPassesPerSemesterUseCase @Inject constructor(
    private val statisticsRepository: IStatisticsRepository,
    private val passEntityMapper: PassEntityMapper
) : IReadGroupMemberPassesPerSemesterUseCase {

    override suspend fun readGroupMemberPassesPerSemester(groupMemberId: Int): Answer<List<PassModel>> {
        return statisticsRepository.readGroupMemberPassesPerSemester(groupMemberId)
            .suspendMap { list -> list.map { passEntityMapper.map(it) } }
    }
}





















