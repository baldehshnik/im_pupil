package com.sparkfusion.portdomainservices.pupil.portstatistics.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.portdomainservices.pupil.portstatistics.model.PassWithGroupMemberModel

interface IReadPassesOfGroupPerMonthUseCase {

    suspend fun readPassesOfGroupPerMonth(): Answer<List<PassWithGroupMemberModel>>
}