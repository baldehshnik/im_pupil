package com.sparkfusion.domain.pupil.account.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.pupil.portaccount.IAccountRepository
import com.sparkfusion.domain.pupil.account.mapper.ReadPupilAccountEntityMapper
import com.sparkfusion.domain.pupil.port.portaccount.model.ReadPupilAccountModel
import com.sparkfusion.domain.pupil.port.portaccount.usecase.IReadPupilAccountUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ReadPupilAccountUseCase @Inject constructor(
    private val accountRepository: IAccountRepository,
    private val readPupilAccountEntityMapper: ReadPupilAccountEntityMapper
): IReadPupilAccountUseCase {

    override suspend fun readPupilAccount(): Answer<ReadPupilAccountModel?> {
        return accountRepository.readPupilAccount()
            .suspendMap { it?.let { m -> readPupilAccountEntityMapper.map(m) } }
    }
}





















