package com.sparkfusion.features.admin.post.screen.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.features.admin.post.R

@Composable
fun AdditionalBlockItem(
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int,
    contentDescription: String?,
    content: String,
    isActive: Boolean
) {
    val activeColor = if (isActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = if (isActive) 2.dp else 0.dp
        ),
        border = BorderStroke(
            1.dp,
            if (isActive) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.background
        ),
        onClick = {}
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .padding(start = 12.dp, end = 8.dp),
                painter = painterResource(id = iconId),
                contentDescription = contentDescription,
                tint = activeColor
            )

            SFProRoundedText(
                modifier = Modifier.padding(end = 12.dp),
                content = content,
                fontSize = 18.sp,
                color = activeColor
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AdditionalBlockItemPreview() {
    AdditionalBlockItem(
        iconId = R.drawable.date_info_icon,
        contentDescription = null,
        content = "Date",
        isActive = true
    )
}
























