package com.sparkfusion.domainadminservices.magazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portmagazine.IMagazineRepository
import com.sparkfusion.domainadminservices.magazine.mapper.ReadGroupMemberEntityMapper
import com.sparkfusion.portdomainservices.admin.portmagazine.model.ReadGroupMemberModel
import com.sparkfusion.portdomainservices.admin.portmagazine.usecase.IReadGroupMembersUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ReadGroupMembersUseCase @Inject constructor(
    private val magazineRepository: IMagazineRepository,
    private val readGroupMemberEntityMapper: ReadGroupMemberEntityMapper
): IReadGroupMembersUseCase {

    override suspend fun readGroupMembersForMagazine(groupId: Int): Answer<List<ReadGroupMemberModel>> {
        return magazineRepository.readGroupMembersForMagazine(groupId)
            .suspendMap { list -> list.map { readGroupMemberEntityMapper.map(it) } }
    }
}











