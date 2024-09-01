package com.sparkfusion.features.admin.account.screen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.image.ShimmerImageBox
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.features.admin.account.R
import com.sparkfusion.features.admin.account.entity.FavoritePostEntity
import com.sparkfusion.features.admin.account.entity.PostAuthor

@Composable
fun PostItem(
    modifier: Modifier = Modifier,
    post: FavoritePostEntity,
    isDarkModeEnabled: Boolean
) {
    var isAccountIconLoadingCompleted by remember { mutableStateOf(false) }
    val accountIcon = rememberAsyncImagePainter(
        model = R.drawable.info_icon,
        onSuccess = { isAccountIconLoadingCompleted = true },
        onLoading = { isAccountIconLoadingCompleted = false }
    )

    val screenWidth = LocalConfiguration.current.screenWidthDp
    val postImageHeight = screenWidth / 2

    var isPostImageLoadingCompleted by remember { mutableStateOf(false) }
    val postImage = rememberAsyncImagePainter(
        model = R.drawable.info_icon,
        onSuccess = { isPostImageLoadingCompleted = true },
        onLoading = { isPostImageLoadingCompleted = false }
    )

    val contentModifier = Modifier.padding(start = 12.dp, end = 12.dp)

    Column(
        modifier = modifier
            .padding(vertical = 12.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = contentModifier.height(48.dp)
        ) {
            ShimmerImageBox(
                contentDescription = null,
                size = DpSize(44.dp, 44.dp),
                painter = accountIcon,
                isDarkModeEnabled = isDarkModeEnabled,
                isImageAnimationCompleted = isAccountIconLoadingCompleted
            )

            Column(modifier = Modifier.padding(start = 8.dp, top = 2.dp)) {
                SFProRoundedText(
                    content = post.author.name,
                    fontWeight = FontWeight.SemiBold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                SFProRoundedText(
                    content = "24.08.2024 at 15:39",
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = descriptionColor()
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = {}) {
                Icon(
                    tint = MaterialTheme.colorScheme.primary,
                    painter = painterResource(R.drawable.delete_icon),
                    contentDescription = null
                )
            }
        }

        SFProRoundedText(
            modifier = contentModifier.padding(top = 12.dp),
            content = post.title,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        SFProRoundedText(
            modifier = contentModifier.padding(top = 4.dp, bottom = 6.dp),
            content = post.description,
            fontSize = 15.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        ShimmerImageBox(
            shape = RoundedCornerShape(0.dp, 0.dp, 30.dp, 30.dp),
            contentDescription = null,
            size = DpSize(screenWidth.dp, postImageHeight.dp),
            painter = postImage,
            isDarkModeEnabled = isDarkModeEnabled,
            isImageAnimationCompleted = isPostImageLoadingCompleted
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PostItemPreview() {
    PostItem(
        isDarkModeEnabled = false,
        post = FavoritePostEntity(
            "Four title",
            "description of the four favorite post",
            PostAuthor("Brest State Technical University")
        )
    )
}