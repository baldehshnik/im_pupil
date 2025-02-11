package com.sparkfusion.features.admin.admin_details.state

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.features.admin.admin_details.R
import com.sparkfusion.features.admin.admin_details.viewmodel.AdminDetailsViewModel

@Composable
internal fun DeletingAdminStateHandler(
    deleteAdminState: AdminDetailsViewModel.DeleteAdminState,
    onBackStack: () -> Unit
) {
    when (deleteAdminState) {
        AdminDetailsViewModel.DeleteAdminState.Error -> {
            ShowToast(value = stringResource(id = R.string.error))
        }

        AdminDetailsViewModel.DeleteAdminState.Initial -> {}
        AdminDetailsViewModel.DeleteAdminState.Progress -> {
            ShowToast(value = stringResource(id = R.string.deleting))
        }

        AdminDetailsViewModel.DeleteAdminState.Success -> {
            onBackStack()
        }
    }
}



