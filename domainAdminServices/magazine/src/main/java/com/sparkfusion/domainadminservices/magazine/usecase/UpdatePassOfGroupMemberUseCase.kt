package com.sparkfusion.domainadminservices.magazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.admin.portmagazine.IMagazineRepository
import com.sparkfusion.domainadminservices.magazine.mapper.UpdatePassStatusModelMapper
import com.sparkfusion.portdomainservices.admin.portmagazine.model.UpdatePassStatusModel
import com.sparkfusion.portdomainservices.admin.portmagazine.usecase.IUpdatePassOfGroupMemberUseCase
import javax.inject.Inject

internal class UpdatePassOfGroupMemberUseCase @Inject constructor(
    private val magazineRepository: IMagazineRepository,
    private val updatePassStatusModelMapper: UpdatePassStatusModelMapper
): IUpdatePassOfGroupMemberUseCase {

    override suspend fun updatePassOfGroupMember(updatePassStatusEntity: UpdatePassStatusModel): Answer<Unit> {
        return magazineRepository.updatePassOfGroupMember(updatePassStatusModelMapper.map(updatePassStatusEntity))
    }
}