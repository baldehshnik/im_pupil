package com.sparkfusion.domain.admin.port.portservices

import com.sparkfusion.core.common.result.Answer

interface IReadServicesUseCase {

    suspend fun readServices(): Answer<List<ServiceEntity>>
}