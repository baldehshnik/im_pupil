package com.sparkfusion.domainadminservices.schedule.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portschedule.IScheduleRepository
import com.sparkfusion.domainadminservices.schedule.mapper.UpdateScheduleModelMapper
import com.sparkfusion.portdomainservices.admin.portschedule.model.UpdateScheduleModel
import com.sparkfusion.portdomainservices.admin.portschedule.usecase.IUpdateScheduleUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class UpdateScheduleUseCase @Inject constructor(
    private val scheduleRepository: IScheduleRepository,
    private val updateScheduleModelMapper: UpdateScheduleModelMapper
): IUpdateScheduleUseCase {

    override suspend fun updateSchedule(schedule: UpdateScheduleModel): Answer<Unit> {
        return scheduleRepository.updateSchedule(updateScheduleModelMapper.map(schedule))
    }
}