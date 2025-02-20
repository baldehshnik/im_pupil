package com.sparkfusion.domainpupilservices.schedule.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portschedule.IScheduleRepository
import com.sparkfusion.domainpupilservices.schedule.mapper.ScheduleEntityMapper
import com.sparkfusion.portdomainservices.pupil.portschedule.IReadSchedulesUseCase
import com.sparkfusion.portdomainservices.pupil.portschedule.ScheduleModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ReadSchedulesUseCase @Inject constructor(
    private val scheduleRepository: IScheduleRepository,
    private val scheduleEntityMapper: ScheduleEntityMapper
) : IReadSchedulesUseCase {

    override suspend fun readSchedules(): Answer<List<ScheduleModel>> {
        return scheduleRepository.readSchedules()
            .suspendMap { list ->
                list.map {
                    scheduleEntityMapper.map(it)
                }
            }
    }
}