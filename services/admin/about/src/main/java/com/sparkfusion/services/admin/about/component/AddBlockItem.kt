package com.sparkfusion.services.admin.about.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.services.admin.about.R
import com.sparkfusion.services.admin.about.model.AboutBlockModel

@Composable
fun AddBlockItem(
    modifier: Modifier = Modifier,
    item: AboutBlockModel
) {
    Column(
        modifier = modifier
            .padding(start = 24.dp, end = 24.dp, top = 20.dp)
            .fillMaxWidth()
    ) {
        if (item.imageUrl.isEmpty()) {
            Image(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.new_image_icon),
                contentDescription = null
            )
        } else {
            Box(
                modifier = Modifier
                    .height(120.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .background(Color.Gray)
            )
        }

        OutlinedTextField(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp)
                .fillMaxWidth()
                .height(120.dp),
            value = item.info,
            onValueChange = {},
            placeholder = {
                SFProRoundedText(
                    content = "Enter info here..."
                )
            }
        )
    }
}