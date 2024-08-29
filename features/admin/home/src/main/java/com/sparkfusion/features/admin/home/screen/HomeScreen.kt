package com.sparkfusion.features.admin.home.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sparkfusion.features.admin.home.R
import com.sparkfusion.features.admin.home.navigator.IHomeNavigator
import com.sparkfusion.features.admin.home.screen.component.HelloComponent
import com.sparkfusion.features.admin.home.screen.component.TopComponent
import com.sparkfusion.features.admin.home.screen.component.post.PostItem

@Composable
fun HomeScreen(
    navigator: IHomeNavigator,
    modifier: Modifier = Modifier
) {
    val isDarkModeEnabled = isSystemInDarkTheme()

    val listState = rememberLazyListState()
    val isDataLoadingCompleted by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            AnimatedVisibility(visible = !listState.isScrollInProgress) {
                TopComponent(
                    name = "Vladislav",
                    onFilterIconClick = navigator::navigateToFiltersScreen,
                    onNotificationsIconClick = navigator::navigateToNotificationsScreen
                )
            }

            LazyColumn(state = listState) {
                item {
                    HelloComponent()
                }

                items(0) {
                    // real data
                    PostItem(isDarkModeEnabled = isDarkModeEnabled)
                }

                if (!isDataLoadingCompleted) {
                    items(5) {
                        // loading placeholders
                        PostItem(
                            isPlaceholder = true,
                            isDarkModeEnabled = isDarkModeEnabled
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(68.dp))
                }
            }
        }

        AnimatedVisibility(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd),
            visible = !listState.isScrollInProgress
        ) {
            LargeFloatingActionButton(
                onClick = navigator::navigateToPostAddingScreen
            ) {
                Icon(
                    painter = painterResource(R.drawable.round_add),
                    contentDescription = stringResource(R.string.add_post_icon_description)
                )
            }
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