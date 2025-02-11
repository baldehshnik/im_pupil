package com.sparkfusion.services.admin.about.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.services.admin.about.R
import com.sparkfusion.services.admin.about.model.EditAboutBlockModel

@Composable
internal fun AddBlockItem(
    modifier: Modifier = Modifier,
    item: EditAboutBlockModel,
    onValueChange: (String) -> Unit,
    onImageClick: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(start = 24.dp, end = 24.dp, top = 20.dp)
            .fillMaxWidth()
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .clickable { onImageClick() },
            model = item.bitmap ?: item.icon ?: R.drawable.new_image_icon,
            contentDescription = null,
            contentScale = if (item.bitmap == null && item.icon == null) ContentScale.Crop else ContentScale.FillBounds
        )

        OutlinedTextField(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp)
                .fillMaxWidth()
                .height(120.dp),
            value = item.description ?: "",
            onValueChange = onValueChange,
            placeholder = {
                SFProRoundedText(
                    content = stringResource(id = R.string.enter_here)
                )
            }
        )
    }
}

























