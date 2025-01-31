package com.sparkfusion.domainadminservices.schedule.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portschedule.IScheduleRepository
import com.sparkfusion.domainadminservices.schedule.mapper.AddScheduleModelMapper
import com.sparkfusion.portdomainservices.admin.portschedule.model.AddScheduleModel
import com.sparkfusion.portdomainservices.admin.portschedule.usecase.ICreateScheduleUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CreateScheduleUseCase @Inject constructor(
    private val scheduleRepository: IScheduleRepository,
    private val addScheduleModelMapper: AddScheduleModelMapper
): ICreateScheduleUseCase {

    override suspend fun createSchedule(schedule: AddScheduleModel): Answer<Unit> {
        return scheduleRepository.createSchedule(addScheduleModelMapper.map(schedule))
    }
}