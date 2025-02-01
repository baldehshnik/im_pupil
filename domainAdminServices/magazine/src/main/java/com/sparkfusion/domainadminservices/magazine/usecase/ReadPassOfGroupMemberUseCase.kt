package com.sparkfusion.domainadminservices.magazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portmagazine.IMagazineRepository
import com.sparkfusion.domainadminservices.magazine.mapper.ReadGroupMemberWithPassesEntityMapper
import com.sparkfusion.portdomainservices.admin.portmagazine.model.ReadGroupMemberWithPassesModel
import com.sparkfusion.portdomainservices.admin.portmagazine.usecase.IReadPassOfGroupMemberUseCase
import java.time.LocalDate
import javax.inject.Inject

class ReadPassOfGroupMemberUseCase @Inject constructor(
    private val magazineRepository: IMagazineRepository,
    private val readGroupMemberWithPassesEntityMapper: ReadGroupMemberWithPassesEntityMapper
): IReadPassOfGroupMemberUseCase {

    override suspend fun readPassOfGroupMember(
        groupMemberId: Int,
        lessonId: Int
    ): Answer<ReadGroupMemberWithPassesModel> {
        return magazineRepository.readPassOfGroupMember(groupMemberId, lessonId, LocalDate.now())
            .suspendMap { readGroupMemberWithPassesEntityMapper.map(it) }
    }
}



















