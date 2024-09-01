package com.sparkfusion.features.admin.services.screen.component

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.sparkfusion.core.widget.image.ShimmerImageBox
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.text.ShimmerTextBox
import com.sparkfusion.domain.admin.port.portservices.ServiceEntity
import com.sparkfusion.features.admin.services.R
import com.sparkfusion.features.admin.services.entity.EmptyServiceEntity
import com.sparkfusion.features.admin.services.utils.getServiceImageId

@Composable
fun ServiceItem(
    modifier: Modifier = Modifier,
    service: ServiceEntity,
    isDarkModeEnabled: Boolean
) {
    var isImageLoadingCompleted by remember { mutableStateOf(false) }
    val painter = rememberAsyncImagePainter(
        model = if (service.imagePath.isNotBlank()) getServiceImageId(service.imagePath) else service.imagePath,
        onSuccess = { isImageLoadingCompleted = true },
        onLoading = { isImageLoadingCompleted = false }
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ShimmerImageBox(
            shape = RoundedCornerShape(10.dp),
            contentDescription = stringResource(R.string.service_image_description),
            size = DpSize(66.dp, 66.dp),
            painter = painter,
            isDarkModeEnabled = isDarkModeEnabled,
            isImageAnimationCompleted = isImageLoadingCompleted
        )

        ShimmerTextBox(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(top = 4.dp),
            size = DpSize(66.dp, 20.dp),
            isDarkModeEnabled = isDarkModeEnabled,
            isLoadingAnimationCompleted = service == EmptyServiceEntity
        ) {
            SFProRoundedText(
                content = service.title,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}