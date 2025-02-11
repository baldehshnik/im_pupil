package com.sparkfusion.domain.admin.services.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.common.portservices.IServiceRepository
import com.sparkfusion.domain.admin.port.portservices.IReadServicesUseCase
import com.sparkfusion.domain.admin.port.portservices.ServiceEntity
import com.sparkfusion.domain.admin.services.mapper.DataPortServicesMapper
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ReadServicesUseCase @Inject constructor(
    private val dataPortServicesMapper: DataPortServicesMapper,
    private val servicesRepository: IServiceRepository
) : IReadServicesUseCase {

    override suspend fun readServices(): Answer<List<ServiceEntity>> {
        return servicesRepository.readServices()
            .suspendMap { list ->
                list.mapNotNull { dataPortServicesMapper.map(it) }
            }
    }
}
















