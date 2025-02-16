package com.sparkfusion.features.pupil.account.screen

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sparkfusion.core.image_crop.launcher.rememberLauncherForImageCropping
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.features.pupil.account.component.PresentationComponent
import com.sparkfusion.features.pupil.account.component.TopComponent
import com.sparkfusion.features.pupil.account.viewmodel.AccountViewModel

@Composable
fun AccountScreenEnter(
    modifier: Modifier = Modifier,
    onNavigateToImageCroppingScreen: (Bitmap) -> Unit,
    getCroppedBitmap: () -> Bitmap?
) {
    AccountScreen(
        modifier = modifier,
        onNavigateToImageCroppingScreen = onNavigateToImageCroppingScreen,
        getCroppedBitmap = getCroppedBitmap
    )
}

@Composable
internal fun AccountScreen(
    modifier: Modifier = Modifier,
    viewModel: AccountViewModel = hiltViewModel(),
    onNavigateToImageCroppingScreen: (Bitmap) -> Unit,
    getCroppedBitmap: () -> Bitmap?
) {
    LaunchedEffect(Unit) {
        viewModel.read()
    }

    val readingState by viewModel.readingState.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val galleryLauncher = rememberLauncherForImageCropping(
        context = context,
        navigateToImageCrop = {
            onNavigateToImageCroppingScreen(it)
        }
    )

    if (readingState is AccountViewModel.ReadingState.Success && (readingState as AccountViewModel.ReadingState.Success).model == null) {
        ShowToast(value = "Account not active!")
    } else {
        LazyColumn(
            modifier = modifier
                .nestedScroll(rememberNestedScrollInteropConnection())
                .background(Color(0xFFF3F9FF)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                TopComponent(
                    state = readingState,
                    onSettingsClick = { }
                )

                PresentationComponent(
                    modifier = Modifier.fillMaxWidth(),
                    state = readingState,
                    launcher = galleryLauncher
                )
            }
        }
    }
}




























