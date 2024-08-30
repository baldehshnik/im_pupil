package com.sparkfusion.features.admin.services.screen.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.features.admin.services.R
import com.sparkfusion.features.admin.services.entity.ServiceEntity

@Composable
fun ServiceItem(
    modifier: Modifier = Modifier,
    service: ServiceEntity
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(66.dp),
            painter = painterResource(service.iconId),
            contentDescription = stringResource(R.string.service_image_description)
        )

        SFProRoundedText(
            modifier = Modifier.padding(top = 4.dp),
            content = service.name,
            fontSize = 14.sp
        )
    }
}