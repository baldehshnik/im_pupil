package com.sparkfusion.features.pupil.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.domain.pupil.port.porthome.model.ReadInstitutionEventModel

@Composable
internal fun PostItem(
    modifier: Modifier = Modifier,
    post: ReadInstitutionEventModel,
    onItemClick: (id: Int) -> Unit
) {
    Column(
        modifier = modifier
            .padding(start = 24.dp, end = 24.dp, top = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(30.dp))
            .clickable { onItemClick(post.id) }
    ) {
        PostImageIcon(imageUrl = post.image)

        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(0.dp, 0.dp, 30.dp, 30.dp))
                .background(Color(0xFFEEF7FF))
                .fillMaxWidth()
        ) {
            SFProRoundedText(
                modifier = Modifier.padding(top = 12.dp, start = 20.dp, end = 32.dp),
                content = post.title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            SFProRoundedText(
                modifier = Modifier
                    .padding(start = 20.dp, top = 2.dp, bottom = 8.dp)
                    .width(240.dp),
                content = post.description,
                fontWeight = FontWeight.Medium,
                color = descriptionColor(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}