package com.sparkfusion.domainadminservices.magazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portmagazine.IMagazineRepository
import com.sparkfusion.domainadminservices.magazine.mapper.ReadGroupMemberWithPassesEntityMapper
import com.sparkfusion.domainadminservices.magazine.mapper.ReadLessonWithPassStatusEntityMapper
import com.sparkfusion.portdomainservices.admin.portmagazine.model.ReadGroupMemberWithPassesModel
import com.sparkfusion.portdomainservices.admin.portmagazine.usecase.IReadPassesUseCase
import java.time.LocalDate
import javax.inject.Inject

internal class ReadPassesUseCase @Inject constructor(
    private val magazineRepository: IMagazineRepository,
    private val readGroupMemberWithPassesEntityMapper: ReadGroupMemberWithPassesEntityMapper
): IReadPassesUseCase {

    override suspend fun readPasses(
        groupId: Int,
        lessonId: Int
    ): Answer<List<ReadGroupMemberWithPassesModel>> {
        return magazineRepository.readPasses(groupId, lessonId, LocalDate.now())
            .suspendMap { list -> list.map { readGroupMemberWithPassesEntityMapper.map(it) } }
    }


}


















