package com.sparkfusion.features.admin.services.screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sparkfusion.features.admin.services.R
import com.sparkfusion.features.admin.services.entity.NewsEntity
import kotlin.math.ceil

@Composable
fun NewsBlock(
    modifier: Modifier = Modifier,
    news: List<NewsEntity>,
    onItemClick: () -> Unit
) {
    val newsListState = rememberLazyGridState()
    val newsListHeight = remember {
        ceil(news.size.toFloat() / 2).toInt() * 202
    }

    BlockWithTitle(
        modifier = modifier,
        title = stringResource(R.string.news)
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        LazyVerticalGrid(
            modifier = Modifier.height(newsListHeight.dp + 60.dp),
            state = newsListState,
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(news) { item ->
                NewsItem(
                    news = item,
                    onClick = onItemClick
                )
            }
        }
    }
}