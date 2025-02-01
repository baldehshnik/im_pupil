package com.sparkfusion.services.admin.magazine.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText

@Composable
fun SpinnerWithTitleComponent(
    items: List<String>,
    currentItem: String,
    title: String,
    expanded: Boolean,
    onDismiss: () -> Unit,
    onExpanded: () -> Unit,
    onItemClick: (String) -> Unit
) {
    SFProRoundedText(
        content = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(start = 24.dp, top = 16.dp)
    )

    Column {
        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 2.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color.LightGray)
                .clickable { onExpanded() },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SFProRoundedText(
                modifier = Modifier.padding(start = 12.dp, top = 8.dp, bottom = 8.dp),
                content = currentItem,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Icon(
                modifier = Modifier.padding(end = 6.dp),
                painter = painterResource(id = com.sparkfusion.core.widget.R.drawable.round_arrow_drop_down),
                contentDescription = null
            )
        }

        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = expanded,
            onDismissRequest = { onDismiss() }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = { SFProRoundedText(content = item) },
                    onClick = {
                        onItemClick(item)
                        onDismiss()
                    }
                )
            }
        }
    }
}












