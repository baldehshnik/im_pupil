package com.sparkfusion.core.design.screen.faculty

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sparkfusion.core.widget.topbar.TopBar

@Composable
fun FacultySelectionScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onFacultyClick: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            TopBar(
                title = "Faculty",
                onBackClick = onBackClick
            )
        }

        items(4) {
            FacultyItem(
                content = "Brest State Technical University",
                onItemClick = { onFacultyClick(1) }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun FacultySelectionScreenPreview() {
    FacultySelectionScreen(
        onBackClick = {},
        onFacultyClick = {}
    )
}
