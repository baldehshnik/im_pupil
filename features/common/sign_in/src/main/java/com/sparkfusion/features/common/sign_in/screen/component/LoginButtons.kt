package com.sparkfusion.features.common.sign_in.screen.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sparkfusion.core.widget.text.SFProRoundedText
import com.sparkfusion.features.common.sign_in.R

@Composable
fun LoginButtons(
    modifier: Modifier = Modifier,
    onPasswordRecoveryClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    val containerColor = when {
        isSystemInDarkTheme() -> MaterialTheme.colorScheme.primary
        else -> Color.Black
    }
    val textColor = when {
        isSystemInDarkTheme() -> MaterialTheme.colorScheme.onPrimary
        else -> Color.White
    }
    val passwordButtonTextColor = when {
        isSystemInDarkTheme() -> MaterialTheme.colorScheme.outline
        else -> Color.DarkGray
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier.padding(top = 14.dp),
            onClick = onLoginClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = containerColor
            )
        ) {
            SFProRoundedText(
                modifier = Modifier.padding(horizontal = 48.dp, vertical = 4.dp),
                content = stringResource(R.string.login),
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = textColor
            )
        }

        TextButton(
            modifier = Modifier.padding(top = 2.dp),
            onClick = onPasswordRecoveryClick
        ) {
            SFProRoundedText(
                content = stringResource(R.string.forget_password),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = passwordButtonTextColor
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        TextButton(
            modifier = Modifier.padding(bottom = 8.dp),
            onClick = onRegisterClick
        ) {
            SFProRoundedText(
                content = stringResource(R.string.register_new_account),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginButtonsPreview() {
    LoginButtons(
        modifier = Modifier.fillMaxSize(),
        onPasswordRecoveryClick = {  },
        onRegisterClick = {  },
        onLoginClick = {  }
    )
}