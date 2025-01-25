package com.sparkfusion.features.admin.confirmation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.sparkfusion.core.widget.image.ShimmerImageBox
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.domain.admin.port.portconfirmation.PupilModel
import com.sparkfusion.features.admin.confirmation.utils.PUPIL_ICON_URL

@Composable
fun PupilItem(
    modifier: Modifier = Modifier,
    pupil: PupilModel,
    onItemClick: () -> Unit
) {
    var isImageLoadingCompleted by remember { mutableStateOf(false) }
    val painter = rememberAsyncImagePainter(
        model = PUPIL_ICON_URL,
        onSuccess = { isImageLoadingCompleted = true },
        onLoading = { isImageLoadingCompleted = false },
        onError = { isImageLoadingCompleted = true }
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 4.dp)
            .clickable { onItemClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        ShimmerImageBox(
            contentDescription = null,
            size = DpSize(56.dp, 56.dp),
            painter = painter,
            isDarkModeEnabled = isSystemInDarkTheme(),
            isImageAnimationCompleted = isImageLoadingCompleted
        )

        Column(
            modifier = Modifier
                .padding(start = 12.dp, top = 6.dp)
                .align(Alignment.Top)
        ) {
            SFProRoundedText(
                content = "Code - ${pupil.code}",
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}























