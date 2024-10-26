package com.sparkfusion.core.design.screen.faculty

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.design.R
import com.sparkfusion.core.widget.text.SFProRoundedText

@Composable
fun FacultyItem(
    content: String,
    onItemClick: (String) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 16.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surfaceContainerHighest)
            .fillMaxWidth()
    ) {
        SFProRoundedText(
            modifier = Modifier
                .width(280.dp)
                .padding(start = 20.dp, top = 12.dp, bottom = 12.dp),
            content = content,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )

        IconButton(
            modifier = Modifier.padding(end = 16.dp),
            onClick = { onItemClick(content) }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.round_arrow_forward),
                contentDescription = null
            )
        }
    }
}