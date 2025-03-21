package com.sparkfusion.features.common.welcome.screen.component

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.resource.animation.bubbleAnimation
import com.sparkfusion.core.resource.color.blackButtonContainerColor
import com.sparkfusion.core.resource.color.blackButtonTextColor
import com.sparkfusion.core.widget.button.RoundedButton
import com.sparkfusion.core.widget.text.SFProRoundedText

@Composable
fun WelcomeButton(
    modifier: Modifier = Modifier,
    information: String,
    onClick: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val scale = remember { Animatable(1f) }

    RoundedButton(
        modifier = modifier
            .scale(scale.value)
            .width(320.dp)
            .height(56.dp)
            .padding(bottom = 4.dp),
        containerColor = blackButtonContainerColor(),
        onClick = {
            scale.bubbleAnimation(coroutineScope, onClick)
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SFProRoundedText(
                modifier = Modifier.padding(vertical = 4.dp),
                content = information,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = blackButtonTextColor()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WelcomeButtonPreview() {
    WelcomeButton(
        information = "Information",
        onClick = { }
    )
}