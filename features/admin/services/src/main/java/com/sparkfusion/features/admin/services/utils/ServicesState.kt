package com.sparkfusion.features.admin.services.utils

import com.sparkfusion.domain.admin.port.portservices.ServiceEntity

sealed interface ServicesState {
    data object Loading : ServicesState
    data object Error : ServicesState

    class Success(private val _data: List<ServiceEntity>): ServicesState {
        val data: List<ServiceEntity> get() = _data
    }
}