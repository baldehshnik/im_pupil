package com.sparkfusion.features.admin.services.viewmodel

import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.viewmodel.DefaultViewModel
import com.sparkfusion.domain.admin.port.portservices.IReadServicesUseCase
import com.sparkfusion.domain.admin.port.portservices.ServiceEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class AdminServicesViewModel @Inject constructor(
    private val readNewsUseCase: IReadServicesUseCase,
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : DefaultViewModel() {

    val enabledServices: Flow<List<ServiceEntity>>
        get() = readNewsUseCase.enabledServices.flowOn(ioDispatcher)
}