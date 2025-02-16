package com.sparkfusion.features.pupil.account.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.resource.color.descriptionColor
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.features.pupil.account.R

@Composable
fun EducationPlaceComponent(
    modifier: Modifier = Modifier,
    speciality: String,
    group: String,
    membersCount: Int
) {
    AccountScreenBlock(
        modifier = modifier,
        title = stringResource(R.string.group_info)
    ) {
        ManagementInfoComponent(
            title = stringResource(R.string.speciality),
            content = speciality
        )

        Column(modifier = modifier.padding(top = 16.dp, start = 32.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                SFProRoundedText(
                    content = stringResource(R.string.group),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp
                )

                SFProRoundedText(
                    modifier = Modifier.padding(end = 24.dp),
                    content = "$membersCount " + stringResource(id = R.string.members),
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = descriptionColor()
                )
            }

            SFProRoundedText(
                modifier = Modifier.padding(top = 4.dp),
                content = group,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(28.dp))
    }
}





















