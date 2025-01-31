package com.sparkfusion.domainadminservices.schedule.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portschedule.IScheduleRepository
import com.sparkfusion.domainadminservices.schedule.mapper.ReadScheduleWithLessonsMapper
import com.sparkfusion.portdomainservices.admin.portschedule.model.ReadScheduleWithLessonsModel
import com.sparkfusion.portdomainservices.admin.portschedule.usecase.IReadScheduleWithLessonsUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ReadScheduleWithLessonsUseCase @Inject constructor(
    private val scheduleRepository: IScheduleRepository,
    private val scheduleWithLessonsEntityMapper: ReadScheduleWithLessonsMapper
): IReadScheduleWithLessonsUseCase {

    override suspend fun readScheduleWithLessons(id: Int): Answer<ReadScheduleWithLessonsModel> {
        return scheduleRepository.readScheduleWithLessons(id)
            .suspendMap { scheduleWithLessonsEntityMapper.map(it) }
    }
}