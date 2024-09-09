package com.sparkfusion.features.common.news.screen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.sparkfusion.core.widget.image.ShimmerImageBox
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.text.ShimmerTextBox
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.features.common.news.R
import com.sparkfusion.features.common.news.entity.StepEntity
import com.sparkfusion.features.common.news.navigator.INewsNavigator
import com.sparkfusion.features.common.news.screen.component.StepComponent
import com.sparkfusion.features.common.news.viewmodel.NewsViewModel

@Composable
fun NewsScreen(
    navigator: INewsNavigator,
    modifier: Modifier = Modifier,
    viewModel: NewsViewModel = hiltViewModel()
) {
    val isDarkModeEnabled = isSystemInDarkTheme()

    var isPresentationImageCompleted by rememberSaveable { mutableStateOf(false) }
    val isNetworkRequestCompleted by rememberSaveable { mutableStateOf(false) }

    val presentationImagePainter = rememberAsyncImagePainter(
        model = "some link",
        onSuccess = { isPresentationImageCompleted = true },
        onLoading = { isPresentationImageCompleted = false }
    )

    val steps = listOf(
        StepEntity("link", 1, "Title 1", "First description"),
        StepEntity("link", 2, "Title 2", "Second description"),
        StepEntity("link", 3, "Title 3", "Third description")
    )

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        item {
            TopBar(
                title = stringResource(R.string.news),
                onBackClick = navigator::popBackStack
            )

            Spacer(modifier = Modifier.height(10.dp))

            ShimmerTextBox(
                modifier = Modifier.padding(start = 24.dp),
                size = DpSize(140.dp, 28.dp),
                isDarkModeEnabled = isDarkModeEnabled,
                isLoadingAnimationCompleted = isNetworkRequestCompleted,
            ) {
                SFProRoundedText(
                    content = "Title",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            ShimmerImageBox(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 8.dp),
                contentDescription = stringResource(R.string.main_info_image_description),
                size = DpSize(LocalConfiguration.current.screenWidthDp.dp, 200.dp),
                shape = RoundedCornerShape(20.dp),
                painter = presentationImagePainter,
                isDarkModeEnabled = isDarkModeEnabled,
                isImageAnimationCompleted = isPresentationImageCompleted
            )

            ShimmerTextBox(
                modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 8.dp),
                size = DpSize(240.dp, 24.dp),
                isDarkModeEnabled = isDarkModeEnabled,
                isLoadingAnimationCompleted = isNetworkRequestCompleted,
            ) {
                SFProRoundedText(
                    content = "Description",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

        }

        items(steps) { item ->
            StepComponent(
                isDarkModeEnabled = isDarkModeEnabled,
                stepEntity = item,
                isNetworkRequestCompleted = isNetworkRequestCompleted
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun NewsScreenPreview() {
    NewsScreen(navigator = object : INewsNavigator {
        override fun popBackStack() {}
    })
}