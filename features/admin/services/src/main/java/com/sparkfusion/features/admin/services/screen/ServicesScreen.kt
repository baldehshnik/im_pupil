package com.sparkfusion.features.admin.services.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sparkfusion.features.admin.services.entity.NewsEntity
import com.sparkfusion.features.admin.services.entity.emptyServices
import com.sparkfusion.features.admin.services.navigator.IServicesNavigator
import com.sparkfusion.features.admin.services.screen.component.AboutApplicationBlock
import com.sparkfusion.features.admin.services.screen.component.NewsBlock
import com.sparkfusion.features.admin.services.screen.component.ServiceItem
import com.sparkfusion.features.admin.services.screen.component.TopComponent
import com.sparkfusion.features.admin.services.viewmodel.AdminServicesViewModel
import kotlin.math.ceil

val tempNews = listOf(
    NewsEntity(Color.Gray, "Some title of the first news"),
    NewsEntity(Color.Yellow, "Some title of the second news"),
    NewsEntity(Color.Blue, "Some title of the third news")
)

@Composable
fun ServicesScreen(
    navigator: IServicesNavigator,
    modifier: Modifier = Modifier,
    viewModel: AdminServicesViewModel = hiltViewModel()
) {
    val services by viewModel.enabledServices.collectAsState(emptyList())

    val screenState = rememberLazyListState()
    val servicesState = rememberLazyGridState()
    val servicesHeight = ceil(services.size.toDouble() / 4).toInt() * 110

    val isDarkModeEnabled = isSystemInDarkTheme()

    Column {
        AnimatedVisibility(visible = !screenState.isScrollInProgress) {
            TopComponent(onSettingsClick = { })
        }

        LazyColumn(
            modifier = modifier.nestedScroll(rememberNestedScrollInteropConnection()),
            state = screenState
        ) {
            item {
                LazyVerticalGrid(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .height(servicesHeight.dp)
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    state = servicesState,
                    columns = GridCells.Fixed(4)
                ) {
                    items(services.ifEmpty { emptyServices }) { item ->
                        ServiceItem(
                            service = item,
                            isDarkModeEnabled = isDarkModeEnabled
                        )
                    }
                }

                AboutApplicationBlock(onReadMoreClick = navigator::navigateToAboutApplicationScreen)

                NewsBlock(
                    modifier = Modifier,
                    news = tempNews,
                    onItemClick = navigator::navigateToNewsScreen
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ServicesScreenPreview() {
    ServicesScreen(navigator = object : IServicesNavigator {
        override fun navigateToNewsScreen() {}
        override fun navigateToAboutApplicationScreen() {}
    })
}