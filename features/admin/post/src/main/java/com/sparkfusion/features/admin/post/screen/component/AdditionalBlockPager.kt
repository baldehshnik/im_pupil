package com.sparkfusion.features.admin.post.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun CenteredItemList() {
    val items = List(20) { "Item $it" }
    val listState = remember { LazyListState() }

    // Ширина экрана в dp
    val screenWidthDp = LocalConfiguration.current.screenWidthDp

    val coroutineScope = rememberCoroutineScope()
    var isScrolling by remember { mutableStateOf(false) }

    val receiver = LocalDensity.current
    LaunchedEffect(listState.isScrollInProgress) {
        if (!listState.isScrollInProgress && isScrolling) {
            // Список остановился после прокрутки
            isScrolling = false

            // Найти элемент, который виден более чем на половину
            val firstVisibleIndex = listState.firstVisibleItemIndex
            val firstVisibleItemOffset = listState.firstVisibleItemScrollOffset

            val itemWidthPx = with(receiver) { screenWidthDp.dp.toPx() }
            val visiblePortion = itemWidthPx - firstVisibleItemOffset

            if (visiblePortion < itemWidthPx / 2 && firstVisibleIndex < items.size - 1) {
                // Если первый видимый элемент меньше чем на половину, то центрируем следующий элемент
                coroutineScope.launch {
                    listState.animateScrollToItem(firstVisibleIndex + 1)
                }
            } else {
                // Иначе центрируем текущий элемент
                coroutineScope.launch {
                    listState.animateScrollToItem(firstVisibleIndex)
                }
            }
        } else if (listState.isScrollInProgress) {
            // Прокрутка началась
            isScrolling = true
        }
    }

    LazyRow(
        state = listState,
        modifier = Modifier.fillMaxWidth()
    ) {
        itemsIndexed(items) { index, item ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(
                        when (index) {
                            1 -> Color.Red
                            2 -> Color.Black
                            3 -> Color.DarkGray
                            4 -> Color.Blue
                            else -> Color.Green
                        }
                    )
                    .size(screenWidthDp.dp, 200.dp) // Используем ширину экрана
            ) {
                // Здесь размещаем контент элемента
            }
        }
    }

    // Центрируем элемент на экране, который виден более чем наполовину
//    val receiver = LocalDensity.current
//    LaunchedEffect(listState) {
//        while (isActive) {
//            delay(100) // Периодически проверяем позицию списка
//
//            val firstVisibleItemIndex = listState.firstVisibleItemIndex
//            val firstVisibleItemScrollOffset = listState.firstVisibleItemScrollOffset
//            val visibleItemWidthPx = with(receiver) { screenWidthDp.toPx() } // Ширина элемента в пикселях
//
//            // Рассчитываем индекс элемента, который должен быть центрирован
//            val centeredIndex = if (firstVisibleItemScrollOffset > visibleItemWidthPx / 2) {
//                firstVisibleItemIndex + 1
//            } else {
//                firstVisibleItemIndex
//            }
//
//            // Центрируем элемент
//            listState.animateScrollToItem(centeredIndex)
//        }
//    }
}