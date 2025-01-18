package com.sparkfusion.features.common.news.screen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.sparkfusion.core.widget.image.ShimmerImageBox
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.toast.ShowToast
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.features.common.news.R
import com.sparkfusion.features.common.news.navigator.INewsNavigator
import com.sparkfusion.features.common.news.screen.component.StepComponent
import com.sparkfusion.features.common.news.viewmodel.NewsViewModel

@Composable
fun NewsScreen(
    navigator: INewsNavigator,
    modifier: Modifier = Modifier,
    viewModel: NewsViewModel = hiltViewModel(),
    newsId: Int
) {
    LaunchedEffect(Unit) {
        viewModel.readNewsInfo(newsId)
    }

    val newsLoadingState by viewModel.newsLoadingState.collectAsStateWithLifecycle()

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
        }

        when (newsLoadingState) {
            NewsViewModel.NewsLoadingState.Error -> {
                item {
                    ShowToast(value = "Error")
                }
            }

            NewsViewModel.NewsLoadingState.Initial -> {}
            NewsViewModel.NewsLoadingState.Progress -> {
                item {
                    CircularProgressIndicator()
                }
            }

            is NewsViewModel.NewsLoadingState.Success -> {
                val state = (newsLoadingState as NewsViewModel.NewsLoadingState.Success).data

                item {
                    var isPresentationImageCompleted by rememberSaveable { mutableStateOf(false) }
                    var isErrorImageLoading by rememberSaveable { mutableStateOf(false) }
                    val presentationImagePainter = rememberAsyncImagePainter(
                        model = state.imageUrl,
                        onSuccess = { isPresentationImageCompleted = true },
                        onLoading = { isPresentationImageCompleted = false },
                        onError = {
                            isPresentationImageCompleted = true
                            isErrorImageLoading = true
                        }
                    )

                    SFProRoundedText(
                        modifier = Modifier.padding(start = 24.dp),
                        content = state.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )

                    ShimmerImageBox(
                        modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = if (isErrorImageLoading) 0.dp else 8.dp),
                        contentDescription = stringResource(R.string.main_info_image_description),
                        size = DpSize(LocalConfiguration.current.screenWidthDp.dp, if (isErrorImageLoading) 0.dp else 200.dp),
                        shape = RoundedCornerShape(20.dp),
                        painter = presentationImagePainter,
                        isDarkModeEnabled = isSystemInDarkTheme(),
                        isImageAnimationCompleted = isPresentationImageCompleted
                    )

                    SFProRoundedText(
                        modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 8.dp),
                        content = state.description ?: "",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(40.dp))
                }

                items(state.guides) { item ->
                    StepComponent(
                        isDarkModeEnabled = isSystemInDarkTheme(),
                        guide = item,
                        guideNumber = state.guides.indexOf(item) + 1
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun NewsScreenPreview() {
    NewsScreen(
        navigator = object : INewsNavigator {
            override fun popBackStack() {}
        },
        newsId = 1
    )
}