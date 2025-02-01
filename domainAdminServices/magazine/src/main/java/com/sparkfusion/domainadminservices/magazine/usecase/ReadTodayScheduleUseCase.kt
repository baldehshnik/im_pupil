package com.sparkfusion.domainadminservices.magazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portmagazine.IMagazineRepository
import com.sparkfusion.domainadminservices.magazine.mapper.ReadLessonEntityMapper
import com.sparkfusion.portdomainservices.admin.portmagazine.model.ReadLessonModel
import com.sparkfusion.portdomainservices.admin.portmagazine.usecase.IReadTodayScheduleUseCase
import java.time.LocalDate
import javax.inject.Inject

class ReadTodayScheduleUseCase @Inject constructor(
    private val magazineRepository: IMagazineRepository,
    private val readLessonEntityMapper: ReadLessonEntityMapper
): IReadTodayScheduleUseCase {

    override suspend fun readTodaySchedule(groupId: Int): Answer<List<ReadLessonModel>> {
        return magazineRepository.readTodaySchedule(groupId, LocalDate.now())
            .suspendMap { list -> list.map { readLessonEntityMapper.map(it) } }
    }
}
















