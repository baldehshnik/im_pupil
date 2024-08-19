package com.sparkfusion.features.common.about.screen.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.features.common.about.entity.CardInfoEntity

@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    cardInfoEntity: CardInfoEntity
) {
    val backgroundColor = when {
        isSystemInDarkTheme() -> MaterialTheme.colorScheme.surface
        else -> Color.White
    }
    val textColor = when {
        isSystemInDarkTheme() -> MaterialTheme.colorScheme.onSurface
        else -> Color.Black
    }

    Column(
        modifier = modifier
    ) {
        SFProRoundedText(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 24.dp),
            content = cardInfoEntity.title,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, top = 12.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = backgroundColor,
                contentColor = textColor
            )
        ) {
            var hasContentDescription = false
            if (cardInfoEntity.contentDescription != null) {
                hasContentDescription = true
                SFProRoundedText(
                    modifier = Modifier.padding(
                        start = 12.dp,
                        end = 12.dp,
                        top = 12.dp,
                        bottom = 4.dp
                    ),
                    content = cardInfoEntity.contentDescription,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            val listModifier = if (hasContentDescription) Modifier.padding(start = 24.dp, end = 12.dp, bottom = 12.dp)
            else Modifier.padding(start = 12.dp, end = 12.dp, bottom = 12.dp, top = 12.dp)
            Column(modifier = listModifier) {
                repeat(cardInfoEntity.items.size) { itemId ->
                    SFProRoundedText(
                        content = cardInfoEntity.items[itemId],
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun InfoCardPreview() {
    InfoCard(
        cardInfoEntity = CardInfoEntity(
            listOf("2. item 1", "1. item 2"),
            "Title",
            "Some content description"
        )
    )
}