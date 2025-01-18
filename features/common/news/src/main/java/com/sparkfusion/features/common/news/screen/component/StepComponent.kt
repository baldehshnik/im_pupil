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
import com.sparkfusion.domain.common.portnews.NewsGuideModel
import com.sparkfusion.features.common.news.R

@Composable
fun StepComponent(
    modifier: Modifier = Modifier,
    isDarkModeEnabled: Boolean,
    guide: NewsGuideModel,
    guideNumber: Int
) {
    var isImageLoadingCompleted by rememberSaveable { mutableStateOf(false) }
    var isImageLoadingError by rememberSaveable { mutableStateOf(false) }
    val presentationImagePainter = rememberAsyncImagePainter(
        model = guide.image,
        onSuccess = { isImageLoadingCompleted = true },
        onLoading = { isImageLoadingCompleted = false },
        onError = {
            isImageLoadingCompleted = true
            isImageLoadingError = true
        }
    )

    Column(
        modifier = modifier
    ) {
        SFProRoundedText(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp),
            content = stringResource(R.string.step_, guideNumber) + guide.title,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )

        ShimmerImageBox(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = if (isImageLoadingError) 0.dp else 8.dp),
            contentDescription = stringResource(R.string.main_info_image_description),
            size = DpSize(LocalConfiguration.current.screenWidthDp.dp, if (isImageLoadingError) 0.dp else 180.dp),
            shape = RoundedCornerShape(20.dp),
            painter = presentationImagePainter,
            isDarkModeEnabled = isDarkModeEnabled,
            isImageAnimationCompleted = isImageLoadingCompleted
        )

        SFProRoundedText(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 8.dp),
            content = guide.description ?: "",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(30.dp))
    }
}














