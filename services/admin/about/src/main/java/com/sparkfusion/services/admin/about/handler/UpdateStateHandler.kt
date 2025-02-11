package com.sparkfusion.services.admin.about.handler

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.services.admin.about.R
import com.sparkfusion.services.admin.about.viewmodel.EditAboutViewModel

@Composable
internal fun UpdateStateHandler(
    updateState: EditAboutViewModel.UpdateState,
    onClearUpdateState: () -> Unit,
    onBackClick: () -> Unit
) {
    when (updateState) {
        EditAboutViewModel.UpdateState.Error -> {
            ShowToast(value = stringResource(id = R.string.error))
            onClearUpdateState()
        }

        EditAboutViewModel.UpdateState.Initial -> {}
        EditAboutViewModel.UpdateState.Progress -> {
            ShowToast(value = stringResource(id = R.string.updating))
        }

        EditAboutViewModel.UpdateState.Success -> {
            onBackClick()
        }
    }
}























