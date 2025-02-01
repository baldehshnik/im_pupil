package com.sparkfusion.domainadminservices.magazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portmagazine.IMagazineRepository
import com.sparkfusion.domainadminservices.magazine.mapper.UpdatePassesStatusModelMapper
import com.sparkfusion.portdomainservices.admin.portmagazine.model.UpdatePassesStatusModel
import com.sparkfusion.portdomainservices.admin.portmagazine.usecase.IUpdatePassesOfGroupMemberUseCase
import javax.inject.Inject

class UpdatePassesOfGroupMemberUseCase @Inject constructor(
    private val magazineRepository: IMagazineRepository,
    private val updatePassesStatusModelMapper: UpdatePassesStatusModelMapper
): IUpdatePassesOfGroupMemberUseCase {

    override suspend fun updatePassesOfGroupMember(updatePassesStatusEntity: UpdatePassesStatusModel): Answer<Unit> {
        return magazineRepository.updatePassesOfGroupMember(updatePassesStatusModelMapper.map(updatePassesStatusEntity))
    }
}























