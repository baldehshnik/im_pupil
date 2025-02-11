package com.sparkfusion.features.admin.home.widget

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.features.admin.home.R

@Composable
fun MoreVertButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopEnd
    ) {

        Box(contentAlignment = Alignment.TopEnd) {
            val contentModifier = Modifier
                .align(Alignment.TopEnd)
                .size(40.dp)

            Canvas(modifier = contentModifier) {
                drawCircle(color = Color.Black, alpha = 0.4f)
            }

            IconButton(
                modifier = contentModifier,
                onClick = { showMenu = true }
            ) {
                Icon(
                    tint = Color.White,
                    painter = painterResource(R.drawable.round_more_vert),
                    contentDescription = stringResource(R.string.more_vert_icon_description)
                )
            }

            DropdownMenu(
                modifier = Modifier.align(Alignment.TopEnd),
                expanded = showMenu,
                onDismissRequest = { showMenu = false },
            ) {
                DropdownMenuItem(
                    modifier = Modifier.align(Alignment.End),
                    text = {
                        SFProRoundedText(content = stringResource(id = R.string.delete))
                    },
                    onClick = {
                        showMenu = false
                        onClick()
                    },
                )
            }
        }
    }
}

















