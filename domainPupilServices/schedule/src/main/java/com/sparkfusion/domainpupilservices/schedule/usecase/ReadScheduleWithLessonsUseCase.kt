package com.sparkfusion.domainpupilservices.schedule.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portschedule.IScheduleRepository
import com.sparkfusion.domainpupilservices.schedule.mapper.ScheduleWithLessonsEntityMapper
import com.sparkfusion.portdomainservices.pupil.portschedule.IReadScheduleWithLessonsUseCase
import com.sparkfusion.portdomainservices.pupil.portschedule.ScheduleWithLessonsModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ReadScheduleWithLessonsUseCase @Inject constructor(
    private val scheduleRepository: IScheduleRepository,
    private val scheduleWithLessonsEntityMapper: ScheduleWithLessonsEntityMapper
) : IReadScheduleWithLessonsUseCase {

    override suspend fun readScheduleWithLessons(id: Int): Answer<ScheduleWithLessonsModel> {
        return scheduleRepository.readScheduleWithLessons(id)
            .suspendMap { scheduleWithLessonsEntityMapper.map(it) }
    }
}





















