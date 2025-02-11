package com.sparkfusion.features.admin.account.screen

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.image_crop.common.IMAGE_CROP_KEY
import com.sparkfusion.core.image_crop.launcher.rememberLauncherForImageCropping
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.features.admin.account.R
import com.sparkfusion.features.admin.account.navigator.IAccountNavigator
import com.sparkfusion.features.admin.account.screen.component.PresentationComponent
import com.sparkfusion.features.admin.account.screen.component.TopComponent
import com.sparkfusion.features.admin.account.screen.state.AdminsReadingStateHandler
import com.sparkfusion.features.admin.account.screen.state.InstitutionReadingStateHandler
import com.sparkfusion.features.admin.account.viewModel.AccountViewModel

@Composable
fun AccountScreenEnter(
    modifier: Modifier = Modifier,
    navigator: IAccountNavigator,
    getCroppedImageBitmap: () -> Bitmap?
) {
    AccountScreen(
        modifier = modifier,
        navigator = navigator,
        getCroppedImageBitmap = getCroppedImageBitmap
    )
}

@Composable
private fun AccountScreen(
    navigator: IAccountNavigator,
    modifier: Modifier = Modifier,
    viewModel: AccountViewModel = hiltViewModel(),
    getCroppedImageBitmap: () -> Bitmap?
) {
    LaunchedEffect(Unit) {
        viewModel.readAccountInfo()
        viewModel.readAdmins()
        viewModel.readInstitutionInfo()
        viewModel.updateAccountImage(getCroppedImageBitmap())
    }

    val accountState by viewModel.accountState.collectAsStateWithLifecycle()
    val adminsState by viewModel.adminsState.collectAsStateWithLifecycle()
    val institutionState by viewModel.institutionState.collectAsStateWithLifecycle()
    val imageState by viewModel.imageState.collectAsStateWithLifecycle()

    var showAllAdministrators by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val galleryLauncher = rememberLauncherForImageCropping(
        context = context,
        navigateToImageCrop = { navigator.navigateToCircleImageCropScreen(IMAGE_CROP_KEY, it) }
    )

    if (imageState == AccountViewModel.ImageState.Error) {
        ShowToast(value = stringResource(id = R.string.failed_image_uploading))
    }

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
                state = accountState,
                launcher = galleryLauncher
            )

            InstitutionReadingStateHandler(institutionState = institutionState)
            AdminsReadingStateHandler(
                adminsState = adminsState,
                accountState = accountState,
                showAllAdministrators = showAllAdministrators,
                onChangeShowAllAdministrators = { showAllAdministrators = it },
                onNavigateToAdminDetailsScreen = { id, accessMode ->
                    navigator.navigateToAdminDetailsScreen(id, accessMode)
                }
            )
        }
    }
}














