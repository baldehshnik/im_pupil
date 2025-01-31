package com.sparkfusion.domainadminservices.schedule.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portschedule.IScheduleRepository
import com.sparkfusion.domainadminservices.schedule.mapper.ScheduleEntityMapper
import com.sparkfusion.portdomainservices.admin.portschedule.model.ScheduleModel
import com.sparkfusion.portdomainservices.admin.portschedule.usecase.IReadScheduleByGroupIdUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ReadScheduleByGroupIdUseCase @Inject constructor(
    private val scheduleRepository: IScheduleRepository,
    private val scheduleEntityMapper: ScheduleEntityMapper
): IReadScheduleByGroupIdUseCase {

    override suspend fun readScheduleByGroupId(groupId: Int): Answer<List<ScheduleModel>> {
        return scheduleRepository.readScheduleByGroupId(groupId)
            .suspendMap { list -> list.map { scheduleEntityMapper.map(it) } }
    }
}