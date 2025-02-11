package com.sparkfusion.domain.pupil.port.portauth.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.domain.pupil.port.portauth.model.AddPupilModel

interface IRegisterPupilUseCase {

    suspend fun signUpPupil(addPupil: AddPupilModel): Answer<Unit>
}