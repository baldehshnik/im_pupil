package com.sparkfusion.features.admin.post.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.features.admin.post.R

@Composable
fun TopBarComponent(
    modifier: Modifier = Modifier,
    onIconClick: () -> Unit,
    onBackClick: () -> Unit
) {
    TopBar(
        modifier = modifier.background(Color.White),
        title = stringResource(id = R.string.adding),
        buttons = {
            IconButton(
                modifier = Modifier
                    .padding(end = 24.dp)
                    .size(24.dp),
                onClick = onIconClick
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.eye_icon),
                    contentDescription = stringResource(id = R.string.preview_button_description)
                )
            }
        },
        onBackClick = onBackClick
    )
}