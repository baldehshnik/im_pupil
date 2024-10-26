package com.sparkfusion.services.admin.students.component

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun BoxScope.FloatingButtonComponent(
    modifier: Modifier = Modifier,
    painter: Painter,
    onClick: () -> Unit
) {
    FloatingActionButton(
        modifier = modifier
            .align(Alignment.BottomEnd)
            .padding(24.dp),
        onClick = onClick
    ) {
        Icon(
            painter = painter,
            contentDescription = null
        )
    }
}
