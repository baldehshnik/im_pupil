package com.sparkfusion.features.common.welcome.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.features.common.welcome.R
import com.sparkfusion.features.common.welcome.navigator.IWelcomeNavigator
import com.sparkfusion.features.common.welcome.screen.component.WelcomeButton
import com.sparkfusion.features.common.welcome.widget.WelcomeButtonsSeparator

@Composable
fun WelcomeScreen(
    navigator: IWelcomeNavigator,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        AsyncImage(
            model = R.drawable.welcome,
            contentDescription = stringResource(R.string.welcome_image),
            modifier = Modifier
                .width(260.dp)
                .height(280.dp)
                .padding(bottom = 40.dp)
        )

        SFProRoundedText(
            content = stringResource(R.string.welcome),
            fontWeight = FontWeight.Black,
            fontSize = 28.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        SFProRoundedText(
            content = stringResource(R.string.select_your_role),
            fontWeight = FontWeight.Medium,
            fontSize = 22.sp,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        WelcomeButton(
            information = stringResource(R.string.administrator),
            onClick = navigator::navigateToSignInScreen
        )

        WelcomeButtonsSeparator(modifier = Modifier.padding(top = 20.dp, bottom = 24.dp))

        WelcomeButton(
            information = stringResource(R.string.pupil),
            onClick = { }
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun WelcomeScreenPreview() {
    WelcomeScreen(navigator = object : IWelcomeNavigator {
        override fun navigateToSignInScreen() {

        }
    })
}