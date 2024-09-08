package com.sparkfusion.features.admin.post.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.features.admin.post.R
import com.sparkfusion.features.admin.post.navigator.IPostAddingNavigator
import com.sparkfusion.features.admin.post.screen.component.AdditionalBlockItem
import com.sparkfusion.features.admin.post.screen.component.CenteredItemList
import com.sparkfusion.features.admin.post.screen.component.DurationBlock
import com.sparkfusion.features.admin.post.screen.component.PostDescriptionBlock
import com.sparkfusion.features.admin.post.screen.component.PostImageBlock
import com.sparkfusion.features.admin.post.screen.component.TopBarComponent
import com.sparkfusion.features.admin.post.utils.getAdditionalBlocks

@Composable
fun PostAddingScreen(
    navigator: IPostAddingNavigator,
    modifier: Modifier = Modifier
) {
    val scroll = rememberScrollState()

    val duration = rememberSaveable { mutableStateOf("") }

    val postOnBehalfOfInstitution = rememberSaveable { mutableStateOf(true) }

    val postTypes = stringArrayResource(id = R.array.post_types)
    val selectedPostType = rememberSaveable { mutableStateOf(postTypes[0]) }

    val durationTypes = stringArrayResource(id = R.array.duration_types)
    val selectedDurationType = rememberSaveable { mutableStateOf(durationTypes[0]) }

    val additionalBlockEntities = getAdditionalBlocks()
    val activeAdditionalBlock = remember { mutableStateOf(additionalBlockEntities[0]) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = modifier
                .background(Color(0xFFF3F9FF))
                .verticalScroll(scroll)
        ) {
            TopBarComponent(
                onIconClick = navigator::navigateToPostPreviewScreen,
                onBackClick = navigator::popBackStack
            )

            PostImageBlock()

            Spacer(modifier = Modifier.height(12.dp))

            PostDescriptionBlock(
                postTypes = postTypes,
                selectedPostType = selectedPostType
            )

            Spacer(modifier = Modifier.height(12.dp))

            Column(
                modifier = modifier
                    .clip(RoundedCornerShape(30.dp))
                    .background(Color.White)
            ) {
                LazyRow(
                    modifier = Modifier.padding(
                        start = 24.dp,
                        end = 24.dp,
                        top = 12.dp,
                        bottom = 18.dp
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items(additionalBlockEntities.size) {
                        val item = additionalBlockEntities[it]
                        AdditionalBlockItem(
                            iconId = item.iconId,
                            contentDescription = item.contentDescription,
                            content = item.title,
                            isActive = item == activeAdditionalBlock.value
                        )

                        if (item != additionalBlockEntities[additionalBlockEntities.size - 1]) {
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }

                CenteredItemList()
            }

            Spacer(modifier = Modifier.height(12.dp))

            DurationBlock(
                durationTypes = durationTypes,
                selectedDurationType = selectedDurationType,
                duration = duration,
                postOnBehalfOfInstitution = postOnBehalfOfInstitution
            )
        }

        AnimatedVisibility(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 16.dp),
            visible = !scroll.isScrollInProgress
        ) {
            ExtendedFloatingActionButton(
                text = {
                    SFProRoundedText(
                        modifier = Modifier.padding(end = 12.dp),
                        content = stringResource(id = R.string.add),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                },
                icon = {
                    Icon(
                        modifier = Modifier.padding(2.dp),
                        painter = painterResource(id = R.drawable.send_icon),
                        contentDescription = stringResource(id = R.string.create_post_button_icon_description)
                    )
                },
                onClick = { }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PostAddingScreenPreview() {
    PostAddingScreen(navigator = object : IPostAddingNavigator {
        override fun navigateToPostPreviewScreen() {}
        override fun popBackStack() {}
    })
}































