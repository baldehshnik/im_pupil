package com.sparkfusion.core.widget.topbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.R
import com.sparkfusion.core.widget.text.SFProRoundedText

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String,
    buttons: @Composable (() -> Unit)? = null,
    onBackClick: () -> Unit
) {
    Row(
        modifier = modifier
            .height(90.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .padding(start = 24.dp)
                .size(24.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.round_arrow_back),
                contentDescription = stringResource(R.string.back_top_bar_button_description)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        SFProRoundedText(
            modifier = Modifier.padding(end = if (buttons != null) 0.dp else 48.dp),
            content = title,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        buttons?.let { it() }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ToBarPreview() {
    TopBar(
        title = "Title",
        onBackClick = { }
    )
}