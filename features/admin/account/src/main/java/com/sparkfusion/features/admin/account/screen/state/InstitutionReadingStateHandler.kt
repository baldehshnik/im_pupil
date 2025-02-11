package com.sparkfusion.features.admin.account.screen.state

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.features.admin.account.R
import com.sparkfusion.features.admin.account.screen.component.ManagementComponent
import com.sparkfusion.features.admin.account.viewModel.AccountViewModel

@Composable
internal fun InstitutionReadingStateHandler(
    institutionState: AccountViewModel.InstitutionState
) {
    when (institutionState) {
        AccountViewModel.InstitutionState.Error -> {
            ShowToast(value = stringResource(id = R.string.error))
        }

        AccountViewModel.InstitutionState.Initial -> {}
        AccountViewModel.InstitutionState.Progress -> {
            CircularProgressIndicator()
        }

        is AccountViewModel.InstitutionState.Success -> {
            ManagementComponent(
                name = institutionState.data.name,
                address = institutionState.data.address ?: stringResource(id = R.string.not_found),
                phone = institutionState.data.phone ?: stringResource(id = R.string.not_found)
            )
        }
    }
}

























