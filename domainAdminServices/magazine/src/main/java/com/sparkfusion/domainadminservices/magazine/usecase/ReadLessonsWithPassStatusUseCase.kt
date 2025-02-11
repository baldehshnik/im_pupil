package com.sparkfusion.domainadminservices.magazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portmagazine.IMagazineRepository
import com.sparkfusion.domainadminservices.magazine.mapper.ReadLessonWithPassStatusEntityMapper
import com.sparkfusion.portdomainservices.admin.portmagazine.model.ReadLessonWithPassStatusModel
import com.sparkfusion.portdomainservices.admin.portmagazine.usecase.IReadLessonsWithPassStatusUseCase
import java.time.LocalDate
import javax.inject.Inject

internal class ReadLessonsWithPassStatusUseCase @Inject constructor(
    private val magazineRepository: IMagazineRepository,
    private val readLessonWithPassStatusEntityMapper: ReadLessonWithPassStatusEntityMapper
): IReadLessonsWithPassStatusUseCase {

    override suspend fun readLessonsWithPassStatus(
        groupMemberId: Int,
        date: LocalDate
    ): Answer<List<ReadLessonWithPassStatusModel>> {
        return magazineRepository.readLessonsWithPassStatus(groupMemberId, date)
            .suspendMap { list -> list.map { readLessonWithPassStatusEntityMapper.map(it) } }
    }
}























