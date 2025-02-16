package com.sparkfusion.domain.pupil.services.usecase

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.dataport.common.portservices.IServiceRepository
import com.sparkfusion.domain.pupil.port.portservices.model.ServiceModel
import com.sparkfusion.domain.pupil.port.portservices.usecase.IReadServicesUseCase
import com.sparkfusion.domain.pupil.services.mapper.DataPortServicesMapper
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
internal class ReadServicesUseCase @Inject constructor(
    private val servicesRepository: IServiceRepository,
    private val servicesEntityMapper: DataPortServicesMapper
) : IReadServicesUseCase {

    override suspend fun readServices(): Answer<List<ServiceModel>> {
        return servicesRepository.readServices()
            .suspendMap { list ->
                list.mapNotNull {
                    servicesEntityMapper.map(it)
                }
            }
    }
}


























