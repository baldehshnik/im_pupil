package com.sparkfusion.domain.admin.port.portauth

import com.sparkfusion.core.common.result.Answer

interface ISignUpUseCase {

    suspend fun signUp(signUpModel: SignUpModel): Answer<Unit>
}

