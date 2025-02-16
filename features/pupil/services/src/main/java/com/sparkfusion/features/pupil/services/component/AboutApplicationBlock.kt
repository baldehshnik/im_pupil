package com.sparkfusion.features.pupil.services.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.features.pupil.services.R

@Composable
fun AboutApplicationBlock(
    modifier: Modifier = Modifier,
    onReadMoreClick: () -> Unit
) {
    BlockWithTitle(
        modifier = modifier.fillMaxWidth(),
        title = stringResource(R.string.about_application)
    ) {
        Card(
            modifier = Modifier.padding(top = 8.dp),
            onClick = onReadMoreClick,
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.5.dp)
        ) {
            SFProRoundedText(
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 12.dp),
                content = stringResource(R.string.welcome_presentation_text),
                fontWeight = FontWeight.Medium
            )

            SFProRoundedText(
                modifier = Modifier
                    .clickable(onClick = onReadMoreClick)
                    .padding(start = 12.dp, bottom = 12.dp),
                content = stringResource(R.string.read_more),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}









