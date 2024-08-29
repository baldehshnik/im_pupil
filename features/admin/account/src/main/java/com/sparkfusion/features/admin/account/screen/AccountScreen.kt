package com.sparkfusion.features.admin.account.screen

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.sparkfusion.core.resource.bundle.IMAGE_CROP_KEY
import com.sparkfusion.features.admin.account.navigator.IAccountNavigator

@Composable
fun AccountScreen(
    navigator: IAccountNavigator,
    modifier: Modifier = Modifier
) {
    val croppedImageValue = navigator.getCroppedImageBitmap()
    var showCroppedImage by remember { mutableStateOf(false) }

    val context = LocalContext.current
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            val inputStream = context.contentResolver.openInputStream(it)
            bitmap = BitmapFactory.decodeStream(inputStream)
        }
    }

    LaunchedEffect(croppedImageValue) {
        showCroppedImage = croppedImageValue != null
    }

    LaunchedEffect(bitmap) {
        if (bitmap != null) {
            navigator.navigateToCircleImageCropScreen(IMAGE_CROP_KEY, bitmap)
        }
    }

    Column(modifier = modifier) {
        if (showCroppedImage) {
            Box(
                modifier = Modifier.background(
                    color = Color.White,
                    shape = MaterialTheme.shapes.medium
                )
            ) {
                croppedImageValue?.let {
                    Image(
                        modifier = Modifier
//                            .clip(CircleShape)
                            .fillMaxWidth()
                            .height(180.dp)
//                            .size(128.dp)
                            .padding(8.dp),
                        bitmap = it.asImageBitmap(),
                        contentDescription = "Cropped Image"
                    )
                }
            }
        }

        Text(text = "Account Screen")
        Button(onClick = navigator::navigateToAdminDetailsScreen) {
            Text(text = "Admin Details")
        }
        Button(onClick = navigator::navigateToPostViewingScreen) {
            Text(text = "Admin Post Viewing")
        }
        Button(onClick = { galleryLauncher.launch("image/*") }) {
            Text(text = "Image crop")
        }
    }
}