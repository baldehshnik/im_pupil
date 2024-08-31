package com.sparkfusion.domain.admin.port.portservices

import kotlinx.coroutines.flow.Flow

interface IReadServicesUseCase {

    val enabledServices: Flow<List<ServiceEntity>>
}