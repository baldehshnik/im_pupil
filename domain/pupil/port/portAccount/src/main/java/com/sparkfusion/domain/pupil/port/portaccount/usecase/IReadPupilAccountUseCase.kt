package com.sparkfusion.domain.pupil.port.portaccount.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.domain.pupil.port.portaccount.model.ReadPupilAccountModel

interface IReadPupilAccountUseCase {

    suspend fun readPupilAccount(): Answer<ReadPupilAccountModel?>
}