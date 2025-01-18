package com.sparkfusion.features.admin.services.screen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.domain.admin.port.portservices.ServiceDestination
import com.sparkfusion.features.admin.services.navigator.IServicesNavigator
import com.sparkfusion.features.admin.services.screen.component.AboutApplicationBlock
import com.sparkfusion.features.admin.services.screen.component.NewsBlock
import com.sparkfusion.features.admin.services.screen.component.ServiceItem
import com.sparkfusion.features.admin.services.screen.component.TopComponent
import com.sparkfusion.features.admin.services.viewmodel.AdminServicesViewModel
import kotlin.math.ceil

@Composable
fun ServicesScreen(
    navigator: IServicesNavigator,
    modifier: Modifier = Modifier,
    viewModel: AdminServicesViewModel = hiltViewModel()
) {
    val newsState by viewModel.newsState.collectAsStateWithLifecycle()
    val servicesState by viewModel.servicesState.collectAsStateWithLifecycle()

    val screenState = rememberLazyListState()
    val isDarkModeEnabled = isSystemInDarkTheme()

    LazyColumn(
        modifier = modifier.fillMaxHeight(),
        state = screenState
    ) {
        item {
            TopComponent(onSettingsClick = { })

            when (servicesState) {
                AdminServicesViewModel.ServicesState.Error -> {
                    ShowToast(value = "Error")
                }

                AdminServicesViewModel.ServicesState.Initial -> {}
                AdminServicesViewModel.ServicesState.Progress -> {
                    CircularProgressIndicator()
                }

                is AdminServicesViewModel.ServicesState.Success -> {
                    val state = servicesState as AdminServicesViewModel.ServicesState.Success
                    val servicesHeight = remember(state.data.size) {
                        ceil(state.data.size.toDouble() / 4).toInt() * 110
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
                        items(state.data) { item ->
                            ServiceItem(
                                service = item,
                                isDarkModeEnabled = isDarkModeEnabled,
                                onItemClick = {
                                    when (item.destination) {
                                        ServiceDestination.MAGAZINE -> navigator.navigateToMagazineService()
                                        ServiceDestination.NOTIFICATIONS -> {}
                                        ServiceDestination.SCHEDULE -> navigator.navigateToScheduleService()
                                        ServiceDestination.STATISTICS -> navigator.navigateToStatisticsService()
                                        ServiceDestination.MESSENGER -> {}
                                        ServiceDestination.SECTIONS -> navigator.navigateToSectionsService()
                                        ServiceDestination.SESSION -> navigator.navigateToSessionService()
                                        ServiceDestination.PRACTICE -> navigator.navigateToPracticeService()
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

            when (newsState) {
                AdminServicesViewModel.NewsState.ConnectionError -> {
                    ShowToast(value = "Connection error")
                }

                AdminServicesViewModel.NewsState.Error -> {
                    ShowToast(value = "Error")
                }

                AdminServicesViewModel.NewsState.Initial -> {}
                AdminServicesViewModel.NewsState.Progress -> {
                    CircularProgressIndicator()
                }

                is AdminServicesViewModel.NewsState.Success -> {
                    NewsBlock(
                        modifier = Modifier,
                        news = (newsState as AdminServicesViewModel.NewsState.Success).data,
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
            override fun navigateToNewsScreen(id: Int) {}
            override fun navigateToAboutApplicationScreen() {}
            override fun navigateToStudentsService() {}
            override fun navigateToStatisticsService() {}
            override fun navigateToAboutService() {}
            override fun navigateToSessionService() {}
            override fun navigateToPracticeService() {}
            override fun navigateToSectionsService() {}
            override fun navigateToMagazineService() {}
            override fun navigateToScheduleService() {}
        }
    )
}