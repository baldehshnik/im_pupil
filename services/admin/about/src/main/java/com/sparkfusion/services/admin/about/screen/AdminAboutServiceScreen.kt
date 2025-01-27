package com.sparkfusion.services.admin.about.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.about.R
import com.sparkfusion.services.admin.about.component.AboutInfoItem
import com.sparkfusion.services.admin.about.viewmodel.AboutViewModel

@Composable
fun AdminAboutServiceScreen(
    modifier: Modifier = Modifier,
    viewModel: AboutViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onEditClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.readBlocks()
    }

    val readState by viewModel.readState.collectAsStateWithLifecycle()

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn {
            item {
                TopBar(title = "About", onBackClick = onBackClick)
            }

            when (readState) {
                AboutViewModel.ReadState.Error -> {
                    item { ShowToast(value = "Error") }
                }

                AboutViewModel.ReadState.Initial -> {}
                AboutViewModel.ReadState.Progress -> {
                    item { CircularProgressIndicator() }
                }

                is AboutViewModel.ReadState.Success -> {
                    val data = (readState as AboutViewModel.ReadState.Success).data
                    items(data) {
                        AboutInfoItem(
                            item = it
                        )
                    }
                }
            }
        }

        if (readState is AboutViewModel.ReadState.Success) {
            FloatingActionButton(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomEnd),
                onClick = onEditClick
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.pencil_icon),
                    contentDescription = null
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AdminAboutServiceScreenPreview() {
    AdminAboutServiceScreen(
        onEditClick = {},
        onBackClick = {}
    )
}
































