package com.sparkfusion.features.admin.services.utils

import com.sparkfusion.domain.admin.port.portservices.NewsEntity

sealed interface NewsState {
    data object Loading : NewsState
    data object Error : NewsState
    data object ConnectionError : NewsState

    class Success(private val _data: List<NewsEntity>) : NewsState {
        val news: List<NewsEntity> get() = _data
    }
}
