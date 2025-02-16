package com.sparkfusion.features.pupil.services.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.domain.pupil.port.portservices.model.NewsModel
import com.sparkfusion.core.resource.R as CoreResource

@Composable
fun NewsItem(
    modifier: Modifier = Modifier,
    news: NewsModel,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(10.dp)

    Card(
        modifier = modifier
            .clip(shape)
            .size(160.dp, 180.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.background,
                            Color.LightGray
//                            news.color
                        ),
                        endY = 1200f
                    ),
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(top = 12.dp, start = 12.dp)
                    .border(
                        border = BorderStroke(
                            1.dp,
                            Color.LightGray
//                            news.color
                        ),
                        shape = shape
                    )
                    .clip(shape)
                    .size(100.dp),
                model = CoreResource.drawable.settings_icon,
                contentDescription = null
            )

            SFProRoundedText(
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 16.dp),
                content = news.title,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}












