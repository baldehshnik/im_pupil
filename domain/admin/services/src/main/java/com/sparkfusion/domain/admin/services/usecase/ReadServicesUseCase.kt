package com.sparkfusion.domain.admin.services.usecase

import com.sparkfusion.domain.admin.port.portservices.IReadServicesUseCase
import com.sparkfusion.domain.admin.port.portservices.ServiceEntity
import com.sparkfusion.domain.admin.services.mapper.DataPortServicesMapper
import com.sparkfusion.dataport.admin.portservices.IAdminServicesRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class ReadServicesUseCase @Inject constructor(
    private val dataPortServicesMapper: DataPortServicesMapper,
    private val repository: IAdminServicesRepository
) : IReadServicesUseCase {

    override val enabledServices: Flow<List<ServiceEntity>>
        get() = repository.enabledServices.map { services ->
            services.mapNotNull { dataPortServicesMapper.map(it) }
        }
}