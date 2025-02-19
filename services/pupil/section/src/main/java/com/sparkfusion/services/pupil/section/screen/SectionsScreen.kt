package com.sparkfusion.services.pupil.section.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.pupil.section.component.SectionItem
import com.sparkfusion.services.pupil.section.viewmodel.SectionsViewModel

@Composable
fun SectionsScreenEnter(
    modifier: Modifier = Modifier,
    onItemClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    SectionsScreen(
        modifier = modifier,
        onItemClick = onItemClick,
        onBackClick = onBackClick
    )
}

@Composable
private fun SectionsScreen(
    modifier: Modifier = Modifier,
    viewModel: SectionsViewModel = hiltViewModel(),
    onItemClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.read()
    }

    val readingState by viewModel.readingState.collectAsStateWithLifecycle()

    SectionsScreenContent(
        modifier = modifier,
        readingState = readingState,
        onClearReadingState = viewModel::clearReadingState,
        onItemClick = onItemClick,
        onBackClick = onBackClick
    )
}

@Composable
private fun SectionsScreenContent(
    modifier: Modifier = Modifier,
    readingState: SectionsViewModel.ReadingState,
    onClearReadingState: () -> Unit,
    onItemClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    when (readingState) {
        SectionsViewModel.ReadingState.Error -> {
            ShowToast(value = "Error")
            onClearReadingState()
        }

        SectionsViewModel.ReadingState.Initial -> {}
        SectionsViewModel.ReadingState.Progress -> {
            CircularProgressIndicator()
        }

        is SectionsViewModel.ReadingState.Success -> {
            LazyColumn(
                modifier = modifier.fillMaxWidth()
            ) {
                item {
                    TopBar(title = "Sections", onBackClick = onBackClick)
                }

                items(readingState.data) {
                    SectionItem(
                        model = it,
                        onItemClick = {
                            onItemClick(it.id)
                        }
                    )
                }
            }
        }
    }
}























