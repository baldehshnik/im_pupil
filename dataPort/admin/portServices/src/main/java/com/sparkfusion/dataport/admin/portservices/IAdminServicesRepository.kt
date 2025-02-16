package com.sparkfusion.dataport.admin.portservices

import com.sparkfusion.core.common.result.Answer
import com.sparkfusion.data.commonentity.CommonNewsDataEntity

interface IAdminServicesRepository {

//    val enabledServices: Flow<List<ServiceEntity>>

    suspend fun loadNews(): Answer<List<CommonNewsDataEntity>>
}

























