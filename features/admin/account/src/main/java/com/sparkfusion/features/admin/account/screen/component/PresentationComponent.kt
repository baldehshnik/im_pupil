package com.sparkfusion.features.admin.account.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.sparkfusion.core.widget.image.ShimmerImageBox
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.text.ShimmerTextBox
import com.sparkfusion.features.admin.account.R

@Composable
fun PresentationComponent(
    modifier: Modifier = Modifier,
    isDarkModeEnabled: Boolean
) {
    val isNameLoadingCompleted by remember { mutableStateOf(false) }
    var isAccountImageLoadingCompleted by remember { mutableStateOf(false) }
    val accountImagePainter = rememberAsyncImagePainter(
        model = com.sparkfusion.core.resource.R.drawable.settings_icon,
        onSuccess = { isAccountImageLoadingCompleted = true },
        onLoading = { isAccountImageLoadingCompleted = false }
    )

    Column(
        modifier = modifier.background(
            color = Color.White,
            shape = RoundedCornerShape(0.dp, 0.dp, 30.dp, 30.dp)
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        ShimmerImageBox(
            contentDescription = stringResource(R.string.account_image_description),
            size = DpSize(124.dp, 124.dp),
            painter = accountImagePainter,
            isDarkModeEnabled = isDarkModeEnabled,
            isImageAnimationCompleted = isAccountImageLoadingCompleted
        )

        ShimmerTextBox(
            modifier = Modifier.padding(top = 10.dp),
            contentAlignment = Alignment.Center,
            size = DpSize(160.dp, 24.dp),
            isDarkModeEnabled = isDarkModeEnabled,
            isLoadingAnimationCompleted = isNameLoadingCompleted
        ) {
            SFProRoundedText(
                content = "Shcherba",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
            )
        }

        ShimmerTextBox(
            modifier = Modifier.padding(top = 2.dp, bottom = 20.dp),
            contentAlignment = Alignment.Center,
            size = DpSize(230.dp, 24.dp),
            isDarkModeEnabled = isDarkModeEnabled,
            isLoadingAnimationCompleted = isNameLoadingCompleted
        ) {
            SFProRoundedText(
                content = "Vladislav Dmitrievich",
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}