package com.sparkfusion.domainpupilservices.magazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portmagazine.IMagazineRepository
import com.sparkfusion.domainpupilservices.magazine.mapper.AccountEntityMapper
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.AccountModel
import com.sparkfusion.portdomainservices.pupil.portmagazine.usecase.IReadPupilAccountUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ReadPupilAccountUseCase @Inject constructor(
    private val magazineRepository: IMagazineRepository,
    private val accountEntityMapper: AccountEntityMapper
) : IReadPupilAccountUseCase {

    override suspend fun readPupilAccount(): Answer<AccountModel?> {
        return magazineRepository.readPupilAccount()
            .suspendMap { model -> model?.let { accountEntityMapper.map(it) } }
    }
}
























