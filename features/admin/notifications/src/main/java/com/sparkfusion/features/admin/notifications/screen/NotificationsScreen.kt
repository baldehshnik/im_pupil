package com.sparkfusion.features.admin.notifications.screen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.domain.admin.port.portnotification.NotificationTypes
import com.sparkfusion.features.admin.notifications.R
import com.sparkfusion.features.admin.notifications.navigator.INotificationsNavigator
import com.sparkfusion.features.admin.notifications.screen.component.NotificationItemComponent
import com.sparkfusion.features.admin.notifications.viewmodel.NotificationsViewModel

@Composable
fun NotificationsScreen(
    navigator: INotificationsNavigator,
    modifier: Modifier = Modifier,
    viewModel: NotificationsViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.readNotifications()
    }

    val readingState by viewModel.readingState.collectAsStateWithLifecycle()

    when (readingState) {
        NotificationsViewModel.ReadingState.Error -> {
            ShowToast(value = stringResource(id = R.string.error))
        }

        NotificationsViewModel.ReadingState.Initial -> {}
        NotificationsViewModel.ReadingState.Progress -> {
            CircularProgressIndicator()
        }

        is NotificationsViewModel.ReadingState.Success -> {
            val state = readingState as NotificationsViewModel.ReadingState.Success
            LazyColumn(
                modifier = modifier.padding(start = 24.dp, end = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    TopBar(
                        title = stringResource(R.string.notifications),
                        onBackClick = navigator::popBackStack,
                        backButtonStartPadding = 0.dp
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        SFProRoundedText(
                            modifier = Modifier.padding(bottom = 4.dp),
                            content = stringResource(id = R.string.unread),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                items(state.unread) {
                    NotificationItemComponent(
                        modifier = Modifier.padding(vertical = 8.dp),
                        title = it.title,
                        description = it.description,
                        isDarkModeEnabled = isSystemInDarkTheme(),
                        icon = it.icon,
                        onItemClick = {
                            viewModel.updateNotificationState(it.id)
                            when (it.type) {
                                NotificationTypes.UNKNOWN -> {}
                                NotificationTypes.NEW_ADMIN -> navigator.navigateToAdminConfirmation()
                                NotificationTypes.NEW_PUPIL -> navigator.navigateToPupilConfirmation()
                            }
                        }
                    )
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        SFProRoundedText(
                            modifier = Modifier.padding(top = 24.dp, bottom = 4.dp),
                            content = stringResource(id = R.string.read),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.Gray
                        )
                    }
                }

                items(state.read) {
                    NotificationItemComponent(
                        modifier = Modifier.padding(vertical = 8.dp),
                        title = it.title,
                        description = it.description,
                        isDarkModeEnabled = isSystemInDarkTheme(),
                        icon = it.icon,
                        onItemClick = {
                            viewModel.updateNotificationState(it.id)
                            when (it.type) {
                                NotificationTypes.UNKNOWN -> {}
                                NotificationTypes.NEW_ADMIN -> navigator.navigateToAdminConfirmation()
                                NotificationTypes.NEW_PUPIL -> navigator.navigateToPupilConfirmation()
                            }
                        }
                    )
                }
            }
        }
    }
}
