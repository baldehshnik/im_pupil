package com.sparkfusion.portdomainservices.pupil.portmagazine.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.pupil.portmagazine.model.AccountModel

interface IReadPupilAccountUseCase {

    suspend fun readPupilAccount(): Answer<AccountModel?>
}