package com.sparkfusion.features.common.sign_in.screen.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.resource.color.textColor
import com.sparkfusion.core.widget.image.ShimmerImageBox
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.text.ShimmerTextBox
import com.sparkfusion.features.common.sign_in.R

const val url = "https://nationaltoday.com/wp-content/uploads/2022/07/76u-min-1200x834.jpg"

@Composable
fun SavedAccountsComponent(
    modifier: Modifier = Modifier,
    isDataLoadingCompleted: Boolean,
    isSystemInDark: Boolean,
    pagerState: PagerState,
    pages: List<String>
) {
    SFProRoundedText(
        modifier.padding(bottom = 16.dp),
        content = stringResource(R.string.saved_accounts),
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp
    )

    HorizontalPager(
        modifier = Modifier,
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 140.dp),
        verticalAlignment = Alignment.CenterVertically
    ) { page ->
        val backgroundColor = MaterialTheme.colorScheme.background
        val isCurrentPageSelected = pagerState.currentPage == page
        val isImageAnimationCompleted = rememberSaveable { mutableStateOf(false) }
        val painter = rememberAsyncImagePainter(
            model = url,
            onSuccess = { isImageAnimationCompleted.value = true },
            onLoading = { isImageAnimationCompleted.value = false }
        )

        Box {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(110.dp)
            ) {
                ShimmerImageBox(
                    contentAlignment = Alignment.Center,
                    contentDescription = stringResource(R.string.saved_account_icon_description),
                    size = if (isCurrentPageSelected) DpSize(96.dp, 96.dp) else DpSize(80.dp, 80.dp),
                    painter = painter,
                    isDarkModeEnabled = isSystemInDark,
                    isImageAnimationCompleted = isImageAnimationCompleted.value
                )

                val textWidth = if (isCurrentPageSelected) 96.dp else 80.dp
                ShimmerTextBox(
                    modifier = Modifier.padding(top = 2.dp),
                    contentAlignment = Alignment.Center,
                    size = DpSize(textWidth, 24.dp),
                    isDarkModeEnabled = isSystemInDark,
                    isLoadingAnimationCompleted = isDataLoadingCompleted,
                ) {
                    SFProRoundedText(
                        modifier = Modifier
                            .width(textWidth)
                            .padding(top = 4.dp),
                        fontWeight = FontWeight.Normal,
                        content = pages[page],
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        color = if (!isCurrentPageSelected) descriptionColor() else textColor()
                    )
                }
            }

            if (!isCurrentPageSelected && isImageAnimationCompleted.value) {
                Canvas(
                    modifier = Modifier
                        .size(80.dp)
                        .alpha(0.6f)
                        .align(Alignment.TopCenter),
                    onDraw = {
                        drawCircle(color = backgroundColor)
                    }
                )
            }
        }
    }
}