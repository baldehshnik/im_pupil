package com.sparkfusion.domain.pupil.port.portservices.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.domain.pupil.port.portservices.model.ServiceModel

interface IReadServicesUseCase {

    suspend fun readServices(): Answer<List<ServiceModel>>
}