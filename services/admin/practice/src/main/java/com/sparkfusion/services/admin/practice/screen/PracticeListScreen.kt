package com.sparkfusion.services.admin.practice.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.services.admin.practice.R
import com.sparkfusion.services.admin.practice.component.PracticeItem

@Composable
fun PracticeListScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onItemClick: () -> Unit,
    onAddClick: () -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp),
            onClick = onAddClick
        ) {
            Icon(
                painter = painterResource(id = R.drawable.plus_icon),
                contentDescription = null
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            item {
                TopBar(title = "Practice", onBackClick = onBackClick)
            }

            items(3) {
                PracticeItem(
                    onItemClick = onItemClick
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PracticeListScreenPreview() {
    PracticeListScreen(
        onBackClick = {},
        onItemClick = {},
        onAddClick = {}
    )
}



















