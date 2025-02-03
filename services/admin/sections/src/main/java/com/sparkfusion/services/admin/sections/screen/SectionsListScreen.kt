package com.sparkfusion.services.admin.sections.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.sparkfusion.services.admin.sections.R
import com.sparkfusion.services.admin.sections.component.SectionItem
import com.sparkfusion.services.admin.sections.viewmodel.SectionsListViewModel

@Composable
fun SectionsListScreen(
    modifier: Modifier = Modifier,
    viewModel: SectionsListViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onItemClick: (Int) -> Unit,
    onAddClick: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.read()
    }

    val readingState by viewModel.readingState.collectAsStateWithLifecycle()

    when (readingState) {
        SectionsListViewModel.ReadingState.Error -> {
            ShowToast(value = "Error")
            viewModel.clearReadingState()
        }
        SectionsListViewModel.ReadingState.Initial -> {}
        SectionsListViewModel.ReadingState.Progress -> {
            CircularProgressIndicator()
        }
        is SectionsListViewModel.ReadingState.Success -> {
            val data = (readingState as SectionsListViewModel.ReadingState.Success).data
            Box(
                modifier = modifier.fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    item {
                        TopBar(title = "Sections", onBackClick = onBackClick)
                    }

                    items(data) {
                        SectionItem(
                            model = it,
                            onItemClick = {
                                onItemClick(it.id)
                            }
                        )
                    }
                }

                FloatingActionButton(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(20.dp),
                    onClick = onAddClick
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.plus_icon),
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SectionsListScreenPreview() {
    SectionsListScreen(
        onBackClick = {},
        onItemClick = {},
        onAddClick = {}
    )
}

























