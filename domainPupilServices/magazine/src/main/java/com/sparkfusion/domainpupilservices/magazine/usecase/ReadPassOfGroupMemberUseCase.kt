package com.sparkfusion.domainpupilservices.magazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portmagazine.IMagazineRepository
import com.sparkfusion.domainpupilservices.magazine.mapper.GroupMemberWithPassEntityMapper
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.GroupMemberWithPassModel
import com.sparkfusion.portdomainservices.pupil.portmagazine.usecase.IReadPassOfGroupMemberUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import java.time.LocalDate
import javax.inject.Inject

@ViewModelScoped
internal class ReadPassOfGroupMemberUseCase @Inject constructor(
    private val magazineRepository: IMagazineRepository,
    private val groupMemberWithPassEntityMapper: GroupMemberWithPassEntityMapper
) : IReadPassOfGroupMemberUseCase {

    override suspend fun readPassOfGroupMember(
        groupMemberId: Int,
        lessonId: Int,
        date: LocalDate
    ): Answer<GroupMemberWithPassModel> {
        return magazineRepository.readPassOfGroupMember(groupMemberId, lessonId, date)
            .suspendMap { groupMemberWithPassEntityMapper.map(it) }
    }
}






















