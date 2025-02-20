package com.sparkfusion.domainpupilservices.magazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portmagazine.IMagazineRepository
import com.sparkfusion.domainpupilservices.magazine.mapper.UpdatePassStatusModelMapper
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.UpdatePassStatusModel
import com.sparkfusion.portdomainservices.pupil.portmagazine.usecase.IUpdatePassOfGroupMemberUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class UpdatePassOfGroupMemberUseCase @Inject constructor(
    private val magazineRepository: IMagazineRepository,
    private val updatePassStatusModelMapper: UpdatePassStatusModelMapper
) : IUpdatePassOfGroupMemberUseCase {

    override suspend fun updatePassOfGroupMember(updatePassStatus: UpdatePassStatusModel): Answer<Unit> {
        return magazineRepository.updatePassOfGroupMember(updatePassStatusModelMapper.map(updatePassStatus))
    }
}

























