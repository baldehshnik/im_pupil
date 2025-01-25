package com.sparkfusion.domain.admin.port.portconfirmation

import com.sparkfusion.core.common.result.Answer

interface IConfirmPupilUseCase {

    suspend fun confirmPupil(id: Int): Answer<Unit>
}