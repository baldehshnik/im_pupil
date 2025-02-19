package com.sparkfusion.services.pupil.about.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.pupil.about.R
import com.sparkfusion.services.pupil.about.viewmodel.AboutViewModel

@Composable
internal fun AboutBlocksListComponent(
    readState: AboutViewModel.ReadState,
    onBackClick: () -> Unit
) {
    LazyColumn {
        item {
            TopBar(title = stringResource(id = R.string.about), onBackClick = onBackClick)
        }

        when (readState) {
            AboutViewModel.ReadState.Error -> {
                item { ShowToast(value = stringResource(id = R.string.error)) }
            }

            AboutViewModel.ReadState.Initial -> {}
            AboutViewModel.ReadState.Progress -> {
                item { CircularProgressIndicator() }
            }

            is AboutViewModel.ReadState.Success -> {
                items(readState.data) {
                    AboutInfoItem(item = it)
                }
            }
        }
    }
}




















