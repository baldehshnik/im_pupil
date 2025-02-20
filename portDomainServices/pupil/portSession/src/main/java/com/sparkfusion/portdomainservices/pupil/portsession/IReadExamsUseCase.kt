package com.sparkfusion.portdomainservices.pupil.portsession

import com.sparkfusion.core.common.result.Answer

interface IReadExamsUseCase {

    suspend fun readExams(): Answer<List<SessionModel>>
}