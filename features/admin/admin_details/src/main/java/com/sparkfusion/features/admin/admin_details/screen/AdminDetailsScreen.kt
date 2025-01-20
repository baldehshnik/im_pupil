package com.sparkfusion.features.admin.admin_details.screen

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import coil.compose.rememberAsyncImagePainter
import com.sparkfusion.core.widget.image.ShimmerImageBox
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.features.admin.admin_details.R
import com.sparkfusion.features.admin.admin_details.navigator.IAdminDetailsNavigator
import com.sparkfusion.features.admin.admin_details.screen.component.DetailItemComponent
import com.sparkfusion.features.admin.admin_details.viewmodel.AdminDetailsViewModel

@Composable
fun AdminDetailsScreen(
    navigator: IAdminDetailsNavigator,
    modifier: Modifier = Modifier,
    viewModel: AdminDetailsViewModel = hiltViewModel(),
    id: Int,
    accessMode: Int
) {
    LaunchedEffect(Unit) {
        viewModel.readAdminDetails(id)
    }

    val adminDetailsState by viewModel.adminDetailsState.collectAsStateWithLifecycle()
    val adminAccessState by viewModel.adminAccessState.collectAsStateWithLifecycle()
    val deleteAdminState by viewModel.adminDeletingState.collectAsStateWithLifecycle()

    var isImageAnimationCompleted by rememberSaveable { mutableStateOf(false) }

    when (deleteAdminState) {
        AdminDetailsViewModel.DeleteAdminState.Error -> {
            ShowToast(value = "Error")
        }

        AdminDetailsViewModel.DeleteAdminState.Initial -> {}
        AdminDetailsViewModel.DeleteAdminState.Progress -> {
            ShowToast(value = "Deleting...")
        }

        AdminDetailsViewModel.DeleteAdminState.Success -> {
            navigator.popBackStack()
        }
    }

    when (adminAccessState) {
        AdminDetailsViewModel.UpdateAdminAccessState.Error -> {
            ShowToast(value = "Error")
        }

        AdminDetailsViewModel.UpdateAdminAccessState.Initial -> {}
        AdminDetailsViewModel.UpdateAdminAccessState.Progress -> {
            ShowToast(value = "Updating...")
        }

        AdminDetailsViewModel.UpdateAdminAccessState.Success -> {
            ShowToast(value = "Success")
        }
    }

    when (adminDetailsState) {
        AdminDetailsViewModel.ReadAdminDetailsState.Error -> {
            ShowToast(value = "Error")
        }

        AdminDetailsViewModel.ReadAdminDetailsState.Initial -> {}
        AdminDetailsViewModel.ReadAdminDetailsState.Progress -> {
            CircularProgressIndicator()
        }

        is AdminDetailsViewModel.ReadAdminDetailsState.Success -> {
            val admin =
                (adminDetailsState as AdminDetailsViewModel.ReadAdminDetailsState.Success).admin

            val painter = rememberAsyncImagePainter(
                model = admin.icon,
                onSuccess = {
                    isImageAnimationCompleted = true
                },
                onLoading = {
                    isImageAnimationCompleted = false
                }
            )

            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopBar(
                    title = stringResource(R.string.details),
                    onBackClick = navigator::popBackStack
                )

                Spacer(modifier = Modifier.height(24.dp))

                ShimmerImageBox(
                    contentAlignment = Alignment.Center,
                    painter = painter,
                    size = DpSize(156.dp, 156.dp),
                    isDarkModeEnabled = isSystemInDarkTheme(),
                    isImageAnimationCompleted = isImageAnimationCompleted,
                    contentDescription = stringResource(R.string.admin_icon_description)
                )

                SFProRoundedText(
                    modifier = Modifier.padding(top = 16.dp),
                    content = admin.lastname,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp
                )

                SFProRoundedText(
                    modifier = Modifier.padding(top = 4.dp),
                    content = admin.firstname + " " + admin.patronymic,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(44.dp))

                DetailItemComponent(
                    title = stringResource(R.string.job_title),
                    textContent = when (admin.accessMode) {
                        1 -> "Assistant"
                        2 -> "Teacher"
                        else -> "Administrator"
                    },
                    imageId = R.drawable.job_title_icon,
                    detailButton = {
                        Log.d("TAGTAG", "ULALA - $accessMode   AND ${admin.accessMode}")
                        if (accessMode > admin.accessMode) {
                            IconButton(onClick = {
                                viewModel.updateAdminAccess()
                            }) {
                                Icon(
                                    tint = MaterialTheme.colorScheme.primary,
                                    painter = painterResource(R.drawable.pencil_icon),
                                    contentDescription = stringResource(R.string.change_admin_type_description)
                                )
                            }
                        }
                    }
                )

                Spacer(modifier = Modifier.height(14.dp))

                DetailItemComponent(
                    title = stringResource(R.string.email),
                    textContent = admin.email,
                    imageId = R.drawable.email_icon,
                )

//        Spacer(modifier = Modifier.height(14.dp))
//
//        DetailItemComponent(
//            isDarkModeEnabled = isSystemInDarkTheme(),
//            isAnimationLoadingCompleted = isDataAnimationLoadingCompleted,
//            title = stringResource(R.string.phone),
//            textContent = "+375 (33) 467-43-12",
//            imageId = R.drawable.phone_icon,
//            width = 170.dp,
//            detailButton = {
//                IconButton(onClick = { }) {
//                    Icon(
//                        tint = MaterialTheme.colorScheme.primary,
//                        painter = painterResource(R.drawable.phone_call_icon),
//                        contentDescription = stringResource(R.string.call_phone_number_description)
//                    )
//                }
//            }
//        )

                if (accessMode > admin.accessMode) {
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(
                        modifier = Modifier.padding(bottom = 48.dp),
                        onClick = { viewModel.deleteAdmin() }
                    ) {
                        SFProRoundedText(
                            content = stringResource(R.string.delete_administrator),
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Red
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AdminDetailsScreenPreview() {
    AdminDetailsScreen(
        navigator = object : IAdminDetailsNavigator {
            override val previousNavBackStackEntry: NavBackStackEntry?
                get() = TODO("Not yet implemented")

            override fun popBackStack() {}
        },
        id = 1,
        accessMode = 1
    )
}