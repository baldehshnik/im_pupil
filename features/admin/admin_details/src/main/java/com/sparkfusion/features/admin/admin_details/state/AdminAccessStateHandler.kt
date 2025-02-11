package com.sparkfusion.features.admin.admin_details.state

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.features.admin.admin_details.R
import com.sparkfusion.features.admin.admin_details.viewmodel.AdminDetailsViewModel

@Composable
internal fun AdminAccessStateHandler(
    adminAccessState: AdminDetailsViewModel.UpdateAdminAccessState
) {
    when (adminAccessState) {
        AdminDetailsViewModel.UpdateAdminAccessState.Error -> {
            ShowToast(value = stringResource(id = R.string.error))
        }

        AdminDetailsViewModel.UpdateAdminAccessState.Initial -> {}
        AdminDetailsViewModel.UpdateAdminAccessState.Progress -> {
            ShowToast(value = stringResource(id = R.string.updating))
        }

        AdminDetailsViewModel.UpdateAdminAccessState.Success -> {
            ShowToast(value = stringResource(id = R.string.success))
        }
    }
}





















