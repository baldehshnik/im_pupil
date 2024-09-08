package com.sparkfusion.features.admin.post.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.check.CheckButtonWidget
import com.sparkfusion.core.widget.spinner.OutlinedDropDownMenu
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.textfield.TextFieldWithoutTitle
import com.sparkfusion.features.admin.post.R

@Composable
fun DurationBlock(
    modifier: Modifier = Modifier,
    durationTypes: Array<String>,
    selectedDurationType: MutableState<String>,
    duration: MutableState<String>,
    postOnBehalfOfInstitution: MutableState<Boolean>
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp))
            .background(Color.White)
    ) {
        SFProRoundedText(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 16.dp, bottom = 2.dp),
            content = stringResource(id = R.string.duration),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            TextFieldWithoutTitle(
                modifier = Modifier.weight(3f),
                placeholder = stringResource(id = R.string.enter_here),
                onValueChange = { duration.value = it },
                keyboardType = KeyboardType.Number
            )

            OutlinedDropDownMenu(
                modifier = Modifier
                    .weight(2f)
                    .padding(end = 24.dp),
                items = durationTypes,
                selectedItem = selectedDurationType
            )
        }

        CheckButtonWidget(
            leftAlignment = false,
            modifier = Modifier.padding(start = 12.dp),
            content = stringResource(id = R.string.post_on_behalf_of_the_institution),
            checked = postOnBehalfOfInstitution.value,
            onCheckedChange = { postOnBehalfOfInstitution.value = !postOnBehalfOfInstitution.value }
        )

        Spacer(modifier = Modifier.height(64.dp))
    }
}