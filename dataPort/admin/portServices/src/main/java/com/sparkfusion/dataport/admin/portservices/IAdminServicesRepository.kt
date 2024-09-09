package com.sparkfusion.dataport.admin.portservices

import com.sparkfusion.data.commonentity.CommonNewsDataEntity
import kotlinx.coroutines.flow.Flow

interface IAdminServicesRepository {

    val enabledServices: Flow<List<ServiceEntity>>

    suspend fun loadNews(): List<CommonNewsDataEntity>
}