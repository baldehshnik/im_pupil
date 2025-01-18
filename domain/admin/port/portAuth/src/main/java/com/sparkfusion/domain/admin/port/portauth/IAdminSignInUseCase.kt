package com.sparkfusion.domain.admin.port.portauth

import com.sparkfusion.core.common.result.Answer

interface IAdminSignInUseCase {

    suspend fun signIn(singInModel: SignInModel): Answer<JwtAuthenticationModel>
}
