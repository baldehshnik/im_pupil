package com.sparkfusion.features.pupil.home.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.features.pupil.home.component.PostItem
import com.sparkfusion.features.pupil.home.component.TopComponent
import com.sparkfusion.features.pupil.home.viewmodel.HomeViewModel

@Composable
fun HomeScreenEnter(
    modifier: Modifier = Modifier,
    onNavigateToPostViewingScreen: (Int) -> Unit
) {
    HomeScreen(
        modifier = modifier,
        onNavigateToPostViewingScreen = onNavigateToPostViewingScreen
    )
}

@Composable
internal fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateToPostViewingScreen: (Int) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.readEvents()
    }

    val institutionEventState by viewModel.institutionEventState.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        item {
            TopComponent()
        }

        when (institutionEventState) {
            HomeViewModel.InstitutionEventState.Error -> {
                item { ShowToast(value = "Error") }
            }

            HomeViewModel.InstitutionEventState.Initial -> {}
            HomeViewModel.InstitutionEventState.Progress -> {
                item {
                    CircularProgressIndicator()
                }
            }

            is HomeViewModel.InstitutionEventState.Success -> {
                val data =
                    (institutionEventState as HomeViewModel.InstitutionEventState.Success).data
                items(data) {
                    PostItem(
                        post = it,
                        onItemClick = { id -> onNavigateToPostViewingScreen(id) }
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(68.dp))
        }
    }
}








































