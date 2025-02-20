package com.sparkfusion.domainpupilservices.magazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portmagazine.IMagazineRepository
import com.sparkfusion.domainpupilservices.magazine.mapper.LessonEntityMapper
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.LessonModel
import com.sparkfusion.portdomainservices.pupil.portmagazine.usecase.IReadTodayScheduleUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import java.time.LocalDate
import javax.inject.Inject

@ViewModelScoped
internal class ReadTodayScheduleUseCase @Inject constructor(
    private val magazineRepository: IMagazineRepository,
    private val lessonEntityMapper: LessonEntityMapper
) : IReadTodayScheduleUseCase {

    override suspend fun readTodaySchedule(): Answer<List<LessonModel>> {
        return magazineRepository.readTodaySchedule(LocalDate.now())
            .suspendMap { list -> list.map { lessonEntityMapper.map(it) } }
    }
}