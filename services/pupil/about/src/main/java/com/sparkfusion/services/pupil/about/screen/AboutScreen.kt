package com.sparkfusion.services.pupil.about.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.services.pupil.about.component.AboutBlocksListComponent
import com.sparkfusion.services.pupil.about.viewmodel.AboutViewModel

@Composable
fun AboutScreenEnter(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    AboutScreen(
        modifier = modifier,
        onBackClick = onBackClick
    )
}

@Composable
private fun AboutScreen(
    modifier: Modifier = Modifier,
    viewModel: AboutViewModel = hiltViewModel(),
    onBackClick: () -> Unit
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
    }
}































