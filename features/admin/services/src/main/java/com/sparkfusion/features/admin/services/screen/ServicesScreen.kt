package com.sparkfusion.features.admin.services.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sparkfusion.domain.admin.port.portservices.ServiceDestination
import com.sparkfusion.features.admin.services.navigator.IServicesNavigator
import com.sparkfusion.features.admin.services.screen.component.AboutApplicationBlock
import com.sparkfusion.features.admin.services.screen.component.NewsBlock
import com.sparkfusion.features.admin.services.screen.component.ServiceItem
import com.sparkfusion.features.admin.services.screen.component.TopComponent
import com.sparkfusion.features.admin.services.utils.NewsState
import com.sparkfusion.features.admin.services.utils.ServicesState
import com.sparkfusion.features.admin.services.viewmodel.AdminServicesViewModel
import kotlin.math.ceil

@Composable
fun ServicesScreen(
    navigator: IServicesNavigator,
    modifier: Modifier = Modifier,
    viewModel: AdminServicesViewModel = hiltViewModel()
) {
    val uiState = viewModel.initialState()

    val screenState = rememberLazyListState()
    val isDarkModeEnabled = isSystemInDarkTheme()

    LazyColumn(
        modifier = modifier,
        state = screenState
    ) {
        item {
            AnimatedVisibility(visible = !screenState.isScrollInProgress) {
                TopComponent(onSettingsClick = { })
            }

            when (uiState.servicesState) {
                ServicesState.Loading -> {
                    CircularProgressIndicator()
                }
                ServicesState.Error -> {}
                is ServicesState.Success -> {
                    val servicesHeight = remember(uiState.servicesState.data.size) {
                        ceil(uiState.servicesState.data.size.toDouble() / 4).toInt() * 110
                    }

                    LazyVerticalGrid(
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .height(servicesHeight.dp)
                            .fillMaxWidth(),
                        contentPadding = PaddingValues(vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        columns = GridCells.Fixed(4)
                    ) {
                        items(uiState.servicesState.data) { item ->
                            ServiceItem(
                                service = item,
                                isDarkModeEnabled = isDarkModeEnabled,
                                onItemClick = {
                                    when(item.destination) {
                                        ServiceDestination.MAGAZINE -> {}
                                        ServiceDestination.NOTIFICATIONS -> {}
                                        ServiceDestination.SCHEDULE -> {}
                                        ServiceDestination.STATISTICS -> navigator.navigateToStatisticsService()
                                        ServiceDestination.MESSENGER -> {}
                                        ServiceDestination.SECTIONS -> {}
                                        ServiceDestination.SESSION -> {}
                                        ServiceDestination.PRACTICE -> {}
                                        ServiceDestination.ABOUT -> navigator.navigateToAboutService()
                                        ServiceDestination.STUDENTS -> navigator.navigateToStudentsService()
                                        ServiceDestination.SETTINGS -> {}
                                    }
                                }
                            )
                        }
                    }
                }
            }

            AboutApplicationBlock(onReadMoreClick = navigator::navigateToAboutApplicationScreen)

            when(uiState.newsState) {
                NewsState.Loading -> {
                    CircularProgressIndicator()
                }
                NewsState.Error -> {

                }
                NewsState.ConnectionError -> {

                }
                is NewsState.Success -> {
                    NewsBlock(
                        modifier = Modifier,
                        news = uiState.newsState.news,
                        onItemClick = navigator::navigateToNewsScreen
                    )
                }
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
            override fun navigateToStudentsService() {}
            override fun navigateToStatisticsService() {}
            override fun navigateToAboutService() {}
        }
    )
}