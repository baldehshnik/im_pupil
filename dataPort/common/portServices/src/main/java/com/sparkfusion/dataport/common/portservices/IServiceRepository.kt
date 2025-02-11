package com.sparkfusion.dataport.common.portservices

import com.sparkfusion.core.common.result.Answer

interface IServiceRepository {

    suspend fun readServices(): Answer<List<ServiceEntity>>
}