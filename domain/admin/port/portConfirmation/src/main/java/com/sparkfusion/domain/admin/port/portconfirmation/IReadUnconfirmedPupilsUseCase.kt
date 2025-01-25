package com.sparkfusion.domain.admin.port.portconfirmation

import com.sparkfusion.core.common.result.Answer

interface IReadUnconfirmedPupilsUseCase {

    suspend fun readUnconfirmedPupils(): Answer<List<PupilModel>>
}