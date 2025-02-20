package com.sparkfusion.domainpupilservices.statistics.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portstatistics.IStatisticsRepository
import com.sparkfusion.domainpupilservices.statistics.mapper.GroupMemberEntityMapper
import com.sparkfusion.portdomainservices.pupil.portstatistics.model.GroupMemberModel
import com.sparkfusion.portdomainservices.pupil.portstatistics.usecase.IReadGroupMemberByIdUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ReadGroupMemberByIdUseCase @Inject constructor(
    private val statisticsRepository: IStatisticsRepository,
    private val groupMemberEntityMapper: GroupMemberEntityMapper
): IReadGroupMemberByIdUseCase {

    override suspend fun readGroupMemberById(id: Int): Answer<GroupMemberModel> {
        return statisticsRepository.readGroupMemberById(id)
            .suspendMap { groupMemberEntityMapper.map(it) }
    }
}
























