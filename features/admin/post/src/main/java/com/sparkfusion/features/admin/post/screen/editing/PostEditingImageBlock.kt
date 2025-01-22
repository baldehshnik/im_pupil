package com.sparkfusion.features.admin.post.screen.editing

import android.graphics.Bitmap
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.features.admin.post.R

@Composable
fun PostEditingImageBlock(
    modifier: Modifier = Modifier,
    image: Bitmap?,
    initialImageUrl: String,
    onImageChangeClick: () -> Unit
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(0.dp, 0.dp, 30.dp, 30.dp))
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SFProRoundedText(
                content = stringResource(id = R.string.information_image),
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp
            )

            SFProRoundedText(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .clickable { onImageChangeClick() },
                fontSize = 14.sp,
                content = stringResource(id = R.string.change),
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary
            )
        }

        AsyncImage(
            modifier = Modifier
                .padding(top = 4.dp)
                .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
                .height(220.dp)
                .clickable { onImageChangeClick() }
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
            model = image ?: initialImageUrl,
            contentDescription = stringResource(id = R.string.post_image_description)
        )
    }
}


























