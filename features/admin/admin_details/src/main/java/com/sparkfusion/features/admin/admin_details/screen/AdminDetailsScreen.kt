package com.sparkfusion.features.admin.admin_details.screen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
import coil.compose.rememberAsyncImagePainter
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.features.admin.admin_details.R
import com.sparkfusion.features.admin.admin_details.navigator.IAdminDetailsNavigator
import com.sparkfusion.features.admin.admin_details.screen.component.DetailItemComponent
import com.sparkfusion.core.widget.image.ShimmerImageBox
import com.sparkfusion.core.widget.text.ShimmerTextBox

const val link =
    "https://media.springernature.com/lw703/springer-static/image/art%3A10.1038%2F528452a/MediaObjects/41586_2015_Article_BF528452a_Figg_HTML.jpg"

@Composable
fun AdminDetailsScreen(
    navigator: IAdminDetailsNavigator,
    modifier: Modifier = Modifier
) {
    var isImageAnimationCompleted by rememberSaveable { mutableStateOf(false) }
    val isDataAnimationLoadingCompleted by rememberSaveable { mutableStateOf(false) }

    val isDarkModeEnabled = isSystemInDarkTheme()

    val painter = rememberAsyncImagePainter(
        model = link,
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
            isDarkModeEnabled = isDarkModeEnabled,
            isImageAnimationCompleted = isImageAnimationCompleted,
            contentDescription = stringResource(R.string.admin_icon_description)
        )

        ShimmerTextBox(
            modifier = Modifier.padding(top = 16.dp),
            size = DpSize(140.dp, 28.dp),
            contentAlignment = Alignment.Center,
            isDarkModeEnabled = isDarkModeEnabled,
            isLoadingAnimationCompleted = isDataAnimationLoadingCompleted,
        ) {
            SFProRoundedText(
                content = "Shcherba",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
            )
        }

        ShimmerTextBox(
            modifier = Modifier.padding(top = 4.dp),
            size = DpSize(240.dp, 28.dp),
            contentAlignment = Alignment.Center,
            isDarkModeEnabled = isDarkModeEnabled,
            isLoadingAnimationCompleted = isDataAnimationLoadingCompleted,
        ) {
            SFProRoundedText(
                content = "Vladislav" + " " + "Dmitrievich",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(44.dp))

        DetailItemComponent(
            isDarkModeEnabled = isDarkModeEnabled,
            isAnimationLoadingCompleted = isDataAnimationLoadingCompleted,
            title = stringResource(R.string.job_title),
            textContent = "Teacher",
            imageId = R.drawable.job_title_icon,
            detailButton = {
                IconButton(onClick = { }) {
                    Icon(
                        tint = MaterialTheme.colorScheme.primary,
                        painter = painterResource(R.drawable.pencil_icon),
                        contentDescription = stringResource(R.string.change_admin_type_description)
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(14.dp))

        DetailItemComponent(
            isDarkModeEnabled = isDarkModeEnabled,
            isAnimationLoadingCompleted = isDataAnimationLoadingCompleted,
            title = stringResource(R.string.email),
            textContent = "fhdsfjhskd@gmail.com",
            imageId = R.drawable.email_icon,
            width = 200.dp
        )

        Spacer(modifier = Modifier.height(14.dp))

        DetailItemComponent(
            isDarkModeEnabled = isDarkModeEnabled,
            isAnimationLoadingCompleted = isDataAnimationLoadingCompleted,
            title = stringResource(R.string.phone),
            textContent = "+375 (33) 467-43-12",
            imageId = R.drawable.phone_icon,
            width = 170.dp,
            detailButton = {
                IconButton(onClick = { }) {
                    Icon(
                        tint = MaterialTheme.colorScheme.primary,
                        painter = painterResource(R.drawable.phone_call_icon),
                        contentDescription = stringResource(R.string.call_phone_number_description)
                    )
                }
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        TextButton(
            modifier = Modifier.padding(bottom = 48.dp),
            onClick = { }
        ) {
            SFProRoundedText(
                content = stringResource(R.string.delete_administrator),
                fontWeight = FontWeight.ExtraBold,
                color = Color.Red
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AdminDetailsScreenPreview() {
    AdminDetailsScreen(
        navigator = object : IAdminDetailsNavigator {
            override fun popBackStack() {}
        }
    )
}