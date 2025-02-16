package com.sparkfusion.features.pupil.services.screen

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.domain.pupil.port.portservices.model.ServiceDestination
import com.sparkfusion.features.pupil.services.R
import com.sparkfusion.features.pupil.services.component.AboutApplicationBlock
import com.sparkfusion.features.pupil.services.component.NewsBlock
import com.sparkfusion.features.pupil.services.component.ServiceItem
import com.sparkfusion.features.pupil.services.component.TopComponent
import com.sparkfusion.features.pupil.services.viewmodel.ServicesViewModel
import kotlin.math.ceil

@Composable
fun ServicesScreenEnter(
    modifier: Modifier = Modifier,
    onNewsDetailsScreenNavigate: (Int) -> Unit,
    onAboutScreenNavigate: () -> Unit,
    onNavigateToMagazineService: () -> Unit,
    onNavigateToScheduleService: () -> Unit,
    onNavigateToStatisticsService: () -> Unit,
    onNavigateToSectionsService: () -> Unit,
    onNavigateToSessionService: () -> Unit,
    onNavigateToPracticeService: () -> Unit,
    onNavigateToAboutService: () -> Unit,
    onNavigateToStudentsService: () -> Unit
) {
    ServicesScreen(
        modifier = modifier,
        onNewsDetailsScreenNavigate = onNewsDetailsScreenNavigate,
        onAboutScreenNavigate = onAboutScreenNavigate,
        onNavigateToMagazineService = onNavigateToMagazineService,
        onNavigateToScheduleService = onNavigateToScheduleService,
        onNavigateToStatisticsService = onNavigateToStatisticsService,
        onNavigateToSectionsService = onNavigateToSectionsService,
        onNavigateToSessionService = onNavigateToSessionService,
        onNavigateToPracticeService = onNavigateToPracticeService,
        onNavigateToAboutService = onNavigateToAboutService,
        onNavigateToStudentsService = onNavigateToStudentsService
    )
}

@Composable
internal fun ServicesScreen(
    modifier: Modifier = Modifier,
    viewModel: ServicesViewModel = hiltViewModel(),
    onNewsDetailsScreenNavigate: (Int) -> Unit,
    onAboutScreenNavigate: () -> Unit,
    onNavigateToMagazineService: () -> Unit,
    onNavigateToScheduleService: () -> Unit,
    onNavigateToStatisticsService: () -> Unit,
    onNavigateToSectionsService: () -> Unit,
    onNavigateToSessionService: () -> Unit,
    onNavigateToPracticeService: () -> Unit,
    onNavigateToAboutService: () -> Unit,
    onNavigateToStudentsService: () -> Unit
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
                ServicesViewModel.ServicesState.Error -> {
                    ShowToast(value = stringResource(id = R.string.error))
                }

                ServicesViewModel.ServicesState.Initial -> {}
                ServicesViewModel.ServicesState.Progress -> {
                    CircularProgressIndicator()
                }

                is ServicesViewModel.ServicesState.Success -> {
                    val state = servicesState as ServicesViewModel.ServicesState.Success
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
                                        ServiceDestination.MAGAZINE -> onNavigateToMagazineService()
                                        ServiceDestination.NOTIFICATIONS -> {}
                                        ServiceDestination.SCHEDULE -> onNavigateToScheduleService()
                                        ServiceDestination.STATISTICS -> onNavigateToStatisticsService()
                                        ServiceDestination.MESSENGER -> {}
                                        ServiceDestination.SECTIONS -> onNavigateToSectionsService()
                                        ServiceDestination.SESSION -> onNavigateToSessionService()
                                        ServiceDestination.PRACTICE -> onNavigateToPracticeService()
                                        ServiceDestination.ABOUT -> onNavigateToAboutService()
                                        ServiceDestination.STUDENTS -> onNavigateToStudentsService()
                                        ServiceDestination.SETTINGS -> {}
                                    }
                                }
                            )
                        }
                    }
                }
            }

            AboutApplicationBlock(onReadMoreClick = onAboutScreenNavigate)

            when (newsState) {
                ServicesViewModel.NewsState.ConnectionError -> {
                    ShowToast(value = stringResource(id = R.string.connection_error))
                }

                ServicesViewModel.NewsState.Error -> {
                    ShowToast(value = stringResource(id = R.string.error))
                }

                ServicesViewModel.NewsState.Initial -> {}
                ServicesViewModel.NewsState.Progress -> {
                    CircularProgressIndicator()
                }

                is ServicesViewModel.NewsState.Success -> {
                    NewsBlock(
                        modifier = Modifier,
                        news = (newsState as ServicesViewModel.NewsState.Success).data,
                        onItemClick = onNewsDetailsScreenNavigate
                    )
                }
            }
        }
    }
}























