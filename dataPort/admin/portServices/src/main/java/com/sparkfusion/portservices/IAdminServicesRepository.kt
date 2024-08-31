package com.sparkfusion.portservices

import kotlinx.coroutines.flow.Flow

interface IAdminServicesRepository {

    val enabledServices: Flow<List<ServiceEntity>>
}