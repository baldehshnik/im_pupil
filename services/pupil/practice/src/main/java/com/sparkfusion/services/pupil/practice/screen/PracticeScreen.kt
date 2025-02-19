package com.sparkfusion.services.pupil.practice.screen

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
import com.sparkfusion.services.pupil.practice.component.PracticeItem
import com.sparkfusion.services.pupil.practice.viewmodel.PracticeViewModel

@Composable
fun PracticeScreenEnter(
    modifier: Modifier = Modifier,
    onItemClick: (id: Int) -> Unit,
    onBackClick: () -> Unit
) {
    PracticeScreen(
        modifier = modifier,
        onItemClick = onItemClick,
        onBackClick = onBackClick
    )
}

@Composable
private fun PracticeScreen(
    modifier: Modifier = Modifier,
    viewModel: PracticeViewModel = hiltViewModel(),
    onItemClick: (id: Int) -> Unit,
    onBackClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.read()
    }

    val readingState by viewModel.readingState.collectAsStateWithLifecycle()

    when (readingState) {
        PracticeViewModel.ReadingState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearReadingState()
        }

        PracticeViewModel.ReadingState.Initial -> {}
        PracticeViewModel.ReadingState.Progress -> {
            CircularProgressIndicator()
        }

        is PracticeViewModel.ReadingState.Success -> {
            val data = (readingState as PracticeViewModel.ReadingState.Success).data
            LazyColumn(
                modifier = modifier.fillMaxWidth()
            ) {
                item {
                    TopBar(title = "Practice", onBackClick = onBackClick)
                }

                items(data) {
                    PracticeItem(
                        model = it,
                        onItemClick = { onItemClick(it.id) }
                    )
                }
            }
        }
    }
}























