package com.sparkfusion.features.admin.account.screen

import android.graphics.Bitmap
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.features.admin.account.R
import com.sparkfusion.features.admin.account.navigator.IAccountNavigator
import com.sparkfusion.features.admin.account.screen.component.AccountScreenBlock
import com.sparkfusion.features.admin.account.screen.component.AdministratorItem
import com.sparkfusion.features.admin.account.screen.component.ManagementComponent
import com.sparkfusion.features.admin.account.screen.component.PresentationComponent
import com.sparkfusion.features.admin.account.screen.component.TopComponent
import com.sparkfusion.features.admin.account.viewModel.AccountViewModel

@Composable
fun AccountScreen(
    navigator: IAccountNavigator,
    modifier: Modifier = Modifier,
    viewModel: AccountViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.readAccountInfo()
        viewModel.readAdmins()
        viewModel.readInstitutionInfo()
    }

    val accountState by viewModel.accountState.collectAsStateWithLifecycle()
    val adminsState by viewModel.adminsState.collectAsStateWithLifecycle()
    val institutionState by viewModel.institutionState.collectAsStateWithLifecycle()

    var showAllAdministrators by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier
            .nestedScroll(rememberNestedScrollInteropConnection())
            .background(Color(0xFFF3F9FF)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            TopComponent(
                state = institutionState,
                onSettingsClick = { }
            )

            PresentationComponent(
                modifier = Modifier.fillMaxWidth(),
                state = accountState
            )

            when (institutionState) {
                AccountViewModel.InstitutionState.Error -> {
                    ShowToast(value = "Error")
                }

                AccountViewModel.InstitutionState.Initial -> {}
                AccountViewModel.InstitutionState.Progress -> {
                    CircularProgressIndicator()
                }

                is AccountViewModel.InstitutionState.Success -> {
                    val data = (institutionState as AccountViewModel.InstitutionState.Success).data
                    ManagementComponent(
                        name = data.name,
                        address = data.address ?: "Not found",
                        phone = data.phone ?: "Not found"
                    )
                }
            }

            when (adminsState) {
                AccountViewModel.AdminsState.Error -> {
                    ShowToast(value = "Error")
                }

                AccountViewModel.AdminsState.Initial -> {}
                AccountViewModel.AdminsState.Progress -> {
                    CircularProgressIndicator()
                }

                is AccountViewModel.AdminsState.Success -> {
                    val data = (adminsState as AccountViewModel.AdminsState.Success).data
                    val administratorsListHeight =
                        68.dp * if (showAllAdministrators) data.size else { 2 } + 32.dp + 40.dp
                    AccountScreenBlock(
                        modifier = Modifier,
                        title = stringResource(R.string.administrators)
                    ) {
                        LazyColumn(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(administratorsListHeight)
                                .padding(vertical = 12.dp)
                        ) {
                            items(
                                if (data.size > 2 && !showAllAdministrators) 2 else data.size
                            ) {
                                val item = data[it]
                                AdministratorItem(
                                    onMoreInfoClick = { navigator.navigateToAdminDetailsScreen(item.id) },
                                    isDarkModeEnabled = isSystemInDarkTheme(),
                                    admin = item
                                )
                            }

                            item {
                                AnimatedVisibility(visible = data.size > 2) {
                                    TextButton(
                                        modifier = Modifier.padding(top = 4.dp),
                                        onClick = { showAllAdministrators = !showAllAdministrators }
                                    ) {
                                        SFProRoundedText(
                                            content = if (showAllAdministrators) stringResource(R.string.hide)
                                            else stringResource(R.string.show_more),
                                            color = MaterialTheme.colorScheme.primary,
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Medium
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AccountScreenPreview() {
    AccountScreen(
        navigator = object : IAccountNavigator {
            override fun navigateToAdminDetailsScreen(id: Int) {}
            override fun navigateToPostViewingScreen() {}
            override fun <T> navigateToCircleImageCropScreen(key: String, value: T) {}
            override fun getCroppedImageBitmap(): Bitmap? {
                return null
            }
        }
    )
}













