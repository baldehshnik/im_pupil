package com.sparkfusion.im_pupil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.sparkfusion.im_pupil.ui.theme.ImPupilTheme
import com.sparkfusion.navigation.common.host.AppNavHost
import com.sparkfusion.navigation.common.type.AccountType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ImPupilTheme {
                AppNavHost(
                    navController = navController,
                    accountType = AccountType.Common
                )
            }
        }
    }
}