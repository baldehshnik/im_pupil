package com.sparkfusion.domainpupilservices.magazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portmagazine.IMagazineRepository
import com.sparkfusion.domainpupilservices.magazine.mapper.LessonWithPassStatusEntityMapper
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.LessonWithPassStatusModel
import com.sparkfusion.portdomainservices.pupil.portmagazine.usecase.IReadScheduleWithPassesUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import java.time.LocalDate
import javax.inject.Inject

@ViewModelScoped
internal class ReadScheduleWithPassesUseCase @Inject constructor(
    private val magazineRepository: IMagazineRepository,
    private val lessonWithPassStatusEntityMapper: LessonWithPassStatusEntityMapper
) : IReadScheduleWithPassesUseCase {

    override suspend fun readScheduleWithPasses(
        groupMemberId: Int,
        date: LocalDate
    ): Answer<List<LessonWithPassStatusModel>> {
        return magazineRepository.readScheduleWithPasses(groupMemberId, date)
            .suspendMap { list ->
                list.map {
                    lessonWithPassStatusEntityMapper.map(it)
                }
            }
    }
}