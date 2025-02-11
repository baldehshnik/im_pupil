package com.sparkfusion.domain.pupil.port.portauth.usecase

import com.sparkfusion.core.common.result.Answer

interface ICheckAccessTokenUseCase {

    suspend fun checkAccessToken(): Answer<Unit>
}