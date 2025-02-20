package com.sparkfusion.domainpupilservices.magazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portmagazine.IMagazineRepository
import com.sparkfusion.domainpupilservices.magazine.mapper.UpdatePassesStatusModelMapper
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.UpdatePassesStatusModel
import com.sparkfusion.portdomainservices.pupil.portmagazine.usecase.IUpdatePassesUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class UpdatePassesUseCase @Inject constructor(
    private val magazineRepository: IMagazineRepository,
    private val updatePassesStatusModelMapper: UpdatePassesStatusModelMapper
) : IUpdatePassesUseCase {

    override suspend fun updatePasses(updatePassesStatus: UpdatePassesStatusModel): Answer<Unit> {
        return magazineRepository.updatePasses(updatePassesStatusModelMapper.map(updatePassesStatus))
    }
}





















