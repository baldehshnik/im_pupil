package com.sparkfusion.features.common.about.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.core.widget.topbar.TopBar
import com.sparkfusion.features.common.about.R
import com.sparkfusion.features.common.about.entity.CardInfoEntity
import com.sparkfusion.features.common.about.navigator.IAboutApplicationNavigator
import com.sparkfusion.features.common.about.screen.component.InfoCard

@Composable
fun AboutApplicationScreen(
    navigator: IAboutApplicationNavigator,
    modifier: Modifier = Modifier
) {
    val backgroundColor = when {
        isSystemInDarkTheme() -> MaterialTheme.colorScheme.background
        else -> colorResource(R.color.card_light_background)
    }

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .background(color = backgroundColor),
    ) {
        TopBar(
            title = stringResource(R.string.about_application),
            onBackClick = navigator::popBackStack
        )

        SFProRoundedText(
            modifier = Modifier.padding(horizontal = 24.dp),
            content = stringResource(R.string.welcome_to_mobile_app),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )

        val cardInfoEntities = getCardItems()
        Column(modifier = Modifier.padding(bottom = 24.dp)) {
            repeat(cardInfoEntities.size) { index ->
                InfoCard(cardInfoEntity = cardInfoEntities[index])
            }
        }
    }
}

@Composable
private fun getCardItems(): List<CardInfoEntity> {
    val goals = stringArrayResource(R.array.goals)
    val purposes = stringArrayResource(R.array.purposes)
    val features = stringArrayResource(R.array.features)

    return listOf(
        CardInfoEntity(
            goals.toList(),
            stringResource(R.string.development_goals),
            stringResource(R.string.our_goal_is)
        ),
        CardInfoEntity(
            purposes.toList(),
            stringResource(R.string.purpose),
            stringResource(R.string.app_is_designed_for)
        ),
        CardInfoEntity(
            features.toList(),
            stringResource(R.string.app_features),
            null
        ),
        CardInfoEntity(
            emptyList(),
            stringResource(R.string.about_developer),
            stringResource(R.string.app_created_by)
        )
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AboutApplicationScreenPreview() {
    AboutApplicationScreen(
        navigator = object : IAboutApplicationNavigator {
            override fun popBackStack() {}
        }
    )
}