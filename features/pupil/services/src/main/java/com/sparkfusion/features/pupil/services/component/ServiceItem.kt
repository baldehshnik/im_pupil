package com.sparkfusion.features.pupil.services.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.sparkfusion.core.widget.image.ShimmerImageBox
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.text.ShimmerTextBox
import com.sparkfusion.domain.pupil.port.portservices.model.ServiceModel

@Composable
fun ServiceItem(
    modifier: Modifier = Modifier,
    service: ServiceModel,
    isDarkModeEnabled: Boolean,
    onItemClick: () -> Unit
) {
    var isImageLoadingCompleted by remember { mutableStateOf(false) }
    val painter = rememberAsyncImagePainter(
        model = service.imagePath,
        onSuccess = { isImageLoadingCompleted = true },
        onLoading = { isImageLoadingCompleted = false }
    )

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable { onItemClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ShimmerImageBox(
            shape = RoundedCornerShape(10.dp),
            contentDescription = null,
            size = DpSize(66.dp, 66.dp),
            painter = painter,
            isDarkModeEnabled = isDarkModeEnabled,
            isImageAnimationCompleted = isImageLoadingCompleted,
        )

        ShimmerTextBox(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(top = 4.dp),
            size = DpSize(66.dp, 20.dp),
            isDarkModeEnabled = isDarkModeEnabled,
            isLoadingAnimationCompleted = true
        ) {
            SFProRoundedText(
                content = service.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}










