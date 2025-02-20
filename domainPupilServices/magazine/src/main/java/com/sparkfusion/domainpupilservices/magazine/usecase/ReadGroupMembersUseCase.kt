package com.sparkfusion.domainpupilservices.magazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portmagazine.IMagazineRepository
import com.sparkfusion.domainpupilservices.magazine.mapper.GroupMemberEntityMapper
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.GroupMemberModel
import com.sparkfusion.portdomainservices.pupil.portmagazine.usecase.IReadGroupMembersUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ReadGroupMembersUseCase @Inject constructor(
    private val magazineRepository: IMagazineRepository,
    private val groupMemberEntityMapper: GroupMemberEntityMapper
): IReadGroupMembersUseCase {

    override suspend fun readGroupMembers(): Answer<List<GroupMemberModel>> {
        return magazineRepository.readGroupMembers()
            .suspendMap { list -> list.map { groupMemberEntityMapper.map(it) } }
    }
}