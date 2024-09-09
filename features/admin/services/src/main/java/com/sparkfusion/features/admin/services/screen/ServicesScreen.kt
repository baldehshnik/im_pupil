package com.sparkfusion.features.admin.services.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sparkfusion.core.resource.animation.DefaultAnimationNavigationScreenDelay
import com.sparkfusion.features.admin.services.navigator.IServicesNavigator
import com.sparkfusion.features.admin.services.screen.component.AboutApplicationBlock
import com.sparkfusion.features.admin.services.screen.component.ServiceItem
import com.sparkfusion.features.admin.services.screen.component.TopComponent
import com.sparkfusion.features.admin.services.viewmodel.AdminServicesViewModel
import kotlinx.coroutines.delay
import kotlin.math.ceil

//val tempNews = listOf(
//    NewsEntity(Color.Gray, "Some title of the first news"),
//    NewsEntity(Color.Yellow, "Some title of the second news"),
//    NewsEntity(Color.Blue, "Some title of the third news")
//)

@Composable
fun ServicesScreen(
    navigator: IServicesNavigator,
    modifier: Modifier = Modifier,
    viewModel: AdminServicesViewModel = hiltViewModel()
) {
    val services by viewModel.enabledServices.collectAsState(emptyList())
    val news by viewModel.news.collectAsState()

    val screenState = rememberLazyListState()
    val servicesHeight = ceil(services.size.toDouble() / 4).toInt() * 110

    val isDarkModeEnabled = isSystemInDarkTheme()

    var isScreenVisible by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = Unit) {
        viewModel.loadNews()
        delay(DefaultAnimationNavigationScreenDelay)
        isScreenVisible = true
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = screenState
    ) {
        item {
            AnimatedVisibility(visible = !screenState.isScrollInProgress) {
                TopComponent(onSettingsClick = { })
            }

            if (isScreenVisible) {
                LazyVerticalGrid(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .height(servicesHeight.dp)
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    columns = GridCells.Fixed(4)
                ) {
                    items(services) { item ->
                        ServiceItem(
                            service = item,
                            isDarkModeEnabled = isDarkModeEnabled
                        )
                    }
                }

                AboutApplicationBlock(onReadMoreClick = navigator::navigateToAboutApplicationScreen)

//                NewsBlock(
//                    modifier = Modifier,
//                    news = tempNews,
//                    onItemClick =
//                    navigator::navigateToNewsScreen
//                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ServicesScreenPreview() {
    ServicesScreen(
        navigator = object : IServicesNavigator {
            override fun navigateToNewsScreen() {}
            override fun navigateToAboutApplicationScreen() {}
        }
    )
}