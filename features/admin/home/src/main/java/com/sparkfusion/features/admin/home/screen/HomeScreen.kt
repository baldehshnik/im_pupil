package com.sparkfusion.features.admin.home.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sparkfusion.core.resource.animation.DefaultAnimationNavigationScreenDelay
import com.sparkfusion.features.admin.home.R
import com.sparkfusion.features.admin.home.navigator.IHomeNavigator
import com.sparkfusion.features.admin.home.screen.component.HelloComponent
import com.sparkfusion.features.admin.home.screen.component.TopComponent
import com.sparkfusion.features.admin.home.viewmodel.HomeViewModel
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    navigator: IHomeNavigator,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
//    val floatingButtonScale by viewModel.floatingButtonScale
    val coroutineScope = rememberCoroutineScope()
//    val isDarkModeEnabled = isSystemInDarkTheme()

    val listState = rememberLazyListState()
//    val isDataLoadingCompleted by viewModel.isDataLoadingCompleted
//    val posts by viewModel.posts.collectAsState()

    // Оптимизация состояния - состояние видимости FAB кэшируется
    val fabVisibility by remember { derivedStateOf { listState.firstVisibleItemIndex == 0 } }

    var isScreenVisible by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = Unit) {
        delay(DefaultAnimationNavigationScreenDelay)
        isScreenVisible = true
    }

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
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

                if (isScreenVisible) {
                    HelloComponent()
                }
            }

//            items(10) {
//                PostItem(
//                    isPlaceholder = true,
//                    isDarkModeEnabled = isDarkModeEnabled
//                )
//            }
//
//            item {
//                Spacer(modifier = Modifier.height(68.dp))
//            }
        }

        if (isScreenVisible && fabVisibility) {
            LargeFloatingActionButton(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomEnd)
//                    .scale(floatingButtonScale)
                ,
                onClick = {
                    viewModel.onFabClick(navigator, coroutineScope)
                }
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