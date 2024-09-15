package com.sparkfusion.features.admin.account.screen

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.features.admin.account.R
import com.sparkfusion.features.admin.account.entity.AdministratorEntity
import com.sparkfusion.features.admin.account.entity.FavoritePostEntity
import com.sparkfusion.features.admin.account.entity.PostAuthor
import com.sparkfusion.features.admin.account.navigator.IAccountNavigator
import com.sparkfusion.features.admin.account.screen.component.AccountScreenBlock
import com.sparkfusion.features.admin.account.screen.component.AdministratorItem
import com.sparkfusion.features.admin.account.screen.component.ManagementComponent
import com.sparkfusion.features.admin.account.screen.component.PostItem
import com.sparkfusion.features.admin.account.screen.component.PresentationComponent
import com.sparkfusion.features.admin.account.screen.component.TopComponent

val tempFavoritePostEntities = listOf(
    FavoritePostEntity(
        "First title",
        "description of the first favorite post",
        PostAuthor("Brest State Technical University")
    ),
    FavoritePostEntity(
        "Second title",
        "description of the second favorite post",
        PostAuthor("Brest State Technical University")
    ),
    FavoritePostEntity(
        "Four title",
        "description of the four favorite post",
        PostAuthor("Brest State Technical University")
    )
)

@Composable
fun AccountScreen(
    navigator: IAccountNavigator,
    modifier: Modifier = Modifier
) {
    val tempAdministratorsList = remember {
        mutableListOf(
            AdministratorEntity("Kulaga", "Dmitriy"),
            AdministratorEntity("Kulaga", "Dmitriy"),
            AdministratorEntity("Kulaga", "Dmitriy")
        )
    }

    val isDarkModeEnabled = isSystemInDarkTheme()
//    val croppedImageValue = navigator.getCroppedImageBitmap()
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

//    LaunchedEffect(croppedImageValue) {
//        showCroppedImage = croppedImageValue != null
//    }
//
//    LaunchedEffect(bitmap) {
//        if (bitmap != null) {
//            navigator.navigateToCircleImageCropScreen(IMAGE_CROP_KEY, bitmap)
//        }
//    }

    var showAllAdministrators by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier
            .nestedScroll(rememberNestedScrollInteropConnection())
            .background(Color(0xFFF3F9FF)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        if (showCroppedImage) {
//            Box(
//                modifier = Modifier.background(
//                    color = Color.White,
//                    shape = MaterialTheme.shapes.medium
//                )
//            ) {
//                croppedImageValue?.let {
//                    Image(
//                        modifier = Modifier
//                            .clip(CircleShape)
//                            .fillMaxWidth()
//                            .height(180.dp)
//                            .size(128.dp)
//                            .padding(8.dp),
//                        bitmap = it.asImageBitmap(),
//                        contentDescription = "Cropped Image"
//                    )
//                }
//            }
//        }

        item {
            TopComponent(
                content = "BSTU",
                onSettingsClick = { }
            )

            PresentationComponent(
                modifier = Modifier.fillMaxWidth(),
                isDarkModeEnabled = isDarkModeEnabled
            )

            ManagementComponent(
                name = "Brest State Technical University",
                address = "Brest city, Moskovskaya street 267",
                phone = "+375 (33) 340-56-49"
            )

            val administratorsListHeight =
                64 * tempAdministratorsList.size + if (tempAdministratorsList.size > 2) 80 else 92
            AccountScreenBlock(
                modifier = Modifier.height(administratorsListHeight.dp),
                title = stringResource(R.string.administrators)
            ) {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(administratorsListHeight.dp)
                        .padding(vertical = 12.dp)
                ) {
                    items(
                        if (tempAdministratorsList.size > 2 && !showAllAdministrators) 2 else tempAdministratorsList.size
                    ) {
                        AdministratorItem(
                            onMoreInfoClick =
                            navigator::navigateToAdminDetailsScreen,
                            isDarkModeEnabled = isDarkModeEnabled
                        )
                    }

                    item {
                        AnimatedVisibility(visible = tempAdministratorsList.size > 2) {
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

            val favoritePostsHeight = 342 * tempFavoritePostEntities.size + 8
            AccountScreenBlock(
                title = stringResource(R.string.favorites),
                shape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .height(favoritePostsHeight.dp)
                        .fillMaxWidth()
                ) {
                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    items(tempFavoritePostEntities.size) {
                        PostItem(
                            post = tempFavoritePostEntities[it],
                            isDarkModeEnabled = isDarkModeEnabled
                        )

                        Spacer(modifier = Modifier.height(12.dp))
                    }
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
            override fun navigateToAdminDetailsScreen() {}
            override fun navigateToPostViewingScreen() {}
            override fun <T> navigateToCircleImageCropScreen(key: String, value: T) {}
            override fun getCroppedImageBitmap(): Bitmap? {
                return null
            }
        }
    )
}