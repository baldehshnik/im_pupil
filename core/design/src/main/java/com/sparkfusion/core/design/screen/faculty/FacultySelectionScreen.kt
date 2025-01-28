package com.sparkfusion.core.design.screen.faculty

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sparkfusion.core.widget.topbar.TopBar

@Composable
fun FacultySelectionScreen(
    modifier: Modifier = Modifier,
    items: List<String> = emptyList(),
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

        items(items.size) { index ->
            FacultyItem(
                content = items[index],
                onItemClick = { onFacultyClick(index) }
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
