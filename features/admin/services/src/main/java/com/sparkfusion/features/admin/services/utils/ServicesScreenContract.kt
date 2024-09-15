package com.sparkfusion.features.admin.services.utils

import com.sparkfusion.core.common.viewmodel.Intent
import com.sparkfusion.core.common.viewmodel.State

interface ServicesScreenContract {

    data class UIState(
        val newsState: NewsState = NewsState.Loading,
        val servicesState: ServicesState = ServicesState.Loading
    ): State

    sealed interface ServicesIntent : Intent {
        data object ReloadNews : ServicesIntent
        data object ReloadServices : ServicesIntent
    }
}