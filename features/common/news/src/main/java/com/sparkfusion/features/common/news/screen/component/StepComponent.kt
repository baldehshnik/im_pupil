package com.sparkfusion.features.common.news.screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.sparkfusion.core.widget.image.ShimmerImageBox
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.text.ShimmerTextBox
import com.sparkfusion.features.common.news.R
import com.sparkfusion.features.common.news.entity.StepEntity

@Composable
fun StepComponent(
    modifier: Modifier = Modifier,
    isNetworkRequestCompleted: Boolean,
    isDarkModeEnabled: Boolean,
    stepEntity: StepEntity
) {
    var isImageLoadingCompleted by rememberSaveable { mutableStateOf(false) }
    val presentationImagePainter = rememberAsyncImagePainter(
        model = stepEntity.imageLink,
        onSuccess = { isImageLoadingCompleted = true },
        onLoading = { isImageLoadingCompleted = false }
    )

    Column(
        modifier = modifier
    ) {
        ShimmerTextBox(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp),
            size = DpSize(240.dp, 28.dp),
            isDarkModeEnabled = isDarkModeEnabled,
            isLoadingAnimationCompleted = isNetworkRequestCompleted,
        ) {
            SFProRoundedText(
                content = stringResource(R.string.step_, stepEntity.stepNumber) + stepEntity.stepTitle,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        ShimmerImageBox(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 8.dp),
            contentDescription = stringResource(R.string.main_info_image_description),
            size = DpSize(LocalConfiguration.current.screenWidthDp.dp, 180.dp),
            shape = RoundedCornerShape(20.dp),
            painter = presentationImagePainter,
            isDarkModeEnabled = isDarkModeEnabled,
            isImageAnimationCompleted = isImageLoadingCompleted
        )

        ShimmerTextBox(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 8.dp),
            size = DpSize(240.dp, 28.dp),
            isDarkModeEnabled = isDarkModeEnabled,
            isLoadingAnimationCompleted = isNetworkRequestCompleted,
        ) {
            SFProRoundedText(
                content = stepEntity.stepDescription,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}