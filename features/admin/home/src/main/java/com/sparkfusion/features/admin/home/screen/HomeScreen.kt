package com.sparkfusion.features.admin.home.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.features.admin.home.R
import com.sparkfusion.features.admin.home.navigator.IHomeNavigator
import com.sparkfusion.features.admin.home.screen.component.HelloComponent
import com.sparkfusion.features.admin.home.screen.component.TopComponent
import com.sparkfusion.features.admin.home.screen.component.post.PostItem
import com.sparkfusion.features.admin.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    navigator: IHomeNavigator,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.readEvents()
    }

    val institutionEventState by viewModel.institutionEventState.collectAsStateWithLifecycle()

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                TopComponent(
                    name = "Vladislav",
                    onFilterIconClick =
                    navigator::navigateToFiltersScreen,
                    onNotificationsIconClick =
                    navigator::navigateToNotificationsScreen

                )

                HelloComponent()
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
                    val state =
                        (institutionEventState as HomeViewModel.InstitutionEventState.Success).data
                    items(state) {
                        PostItem(post = it)
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(68.dp))
            }
        }

        LargeFloatingActionButton(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd),
            onClick = { navigator.navigateToPostAddingScreen() }
        ) {
            Icon(
                painter = painterResource(R.drawable.round_add),
                contentDescription = stringResource(R.string.add_post_icon_description)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        navigator = object : IHomeNavigator {
            override fun navigateToNotificationsScreen() {}
            override fun navigateToPostAddingScreen() {}
            override fun navigateToPostViewingScreen() {}
            override fun navigateToFiltersScreen() {}
        }
    )
}