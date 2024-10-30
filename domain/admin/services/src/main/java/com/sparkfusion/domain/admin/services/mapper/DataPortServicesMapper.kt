package com.sparkfusion.domain.admin.services.mapper

import com.sparkfusion.core.common.dispatchers.IODispatcher
import com.sparkfusion.core.common.mapper.Mapper
import com.sparkfusion.domain.admin.port.portservices.ServiceDestination
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.sparkfusion.domain.admin.port.portservices.ServiceEntity as DomainPortServiceEntity
import com.sparkfusion.dataport.admin.portservices.ServiceEntity as DataPortServiceEntity

class DataPortServicesMapper @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher
) : Mapper<DataPortServiceEntity, DomainPortServiceEntity?> {

    override suspend fun map(input: DataPortServiceEntity): DomainPortServiceEntity? =
        withContext(ioDispatcher) {
            return@withContext with(input) {
                val destination = ServiceDestination.entries.firstOrNull { it.value == destination }
                destination?.let {
                    DomainPortServiceEntity(id, title, imagePath, position, isEnabled, it)
                }
            }
        }
}