package com.sparkfusion.features.admin.post.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.spinner.OutlinedDropDownMenu
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.features.admin.post.R
import com.sparkfusion.features.admin.post.widget.AddingTextField

@Composable
fun PostDescriptionBlock(
    modifier: Modifier = Modifier,
    postTypes: Array<String>,
    selectedPostType: MutableState<String>
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(30.dp))
            .background(Color.White)
    ) {
        AddingTextField(
            title = stringResource(id = R.string.title),
            placeholder = stringResource(id = R.string.enter_here_max_32)
        )

        AddingTextField(
            modifier = Modifier.height(96.dp),
            singleLine = false,
            title = stringResource(id = R.string.description),
            placeholder = stringResource(id = R.string.enter_here)
        )

        SFProRoundedText(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 2.dp),
            content = stringResource(id = R.string.type),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )

        OutlinedDropDownMenu(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 12.dp),
            items = postTypes,
            selectedItem = selectedPostType
        )
    }
}