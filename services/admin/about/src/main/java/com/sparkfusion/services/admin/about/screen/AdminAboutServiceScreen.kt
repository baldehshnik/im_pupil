package com.sparkfusion.services.admin.about.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.services.admin.about.R
import com.sparkfusion.services.admin.about.component.AboutBlocksListComponent
import com.sparkfusion.services.admin.about.viewmodel.AboutViewModel

@Composable
fun AdminAboutServiceScreenEnter(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onEditClick: () -> Unit
) {
    AdminAboutServiceScreen(
        modifier = modifier,
        onBackClick = { onBackClick() },
        onEditClick = { onEditClick() }
    )
}

@Composable
private fun AdminAboutServiceScreen(
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
        AboutBlocksListComponent(
            readState = readState,
            onBackClick = onBackClick
        )

        if (readState is AboutViewModel.ReadState.Success) {
            FloatingActionButton(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomEnd),
                onClick = {
                    viewModel.reload = true
                    onEditClick()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.pencil_icon),
                    contentDescription = null
                )
            }
        }
    }
}


































