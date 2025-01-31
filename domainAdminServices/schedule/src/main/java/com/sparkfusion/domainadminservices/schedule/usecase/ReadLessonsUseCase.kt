package com.sparkfusion.domainadminservices.schedule.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portschedule.IScheduleRepository
import com.sparkfusion.domainadminservices.schedule.mapper.ReadLessonEntityMapper
import com.sparkfusion.portdomainservices.admin.portschedule.model.ReadLessonModel
import com.sparkfusion.portdomainservices.admin.portschedule.usecase.IReadLessonsUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ReadLessonsUseCase @Inject constructor(
    private val scheduleRepository: IScheduleRepository,
    private val lessonEntityMapper: ReadLessonEntityMapper
): IReadLessonsUseCase {

    override suspend fun readLessonsByScheduleId(scheduleId: Int): Answer<List<ReadLessonModel>> {
        return scheduleRepository.readLessonsByScheduleId(scheduleId)
            .suspendMap { list -> list.map { lessonEntityMapper.map(it) } }
    }
}