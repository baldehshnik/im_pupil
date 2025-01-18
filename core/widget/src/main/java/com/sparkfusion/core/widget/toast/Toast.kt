package com.sparkfusion.core.widget.toast

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ShowToast(value: String) {
    val context = LocalContext.current
    Toast.makeText(context, value, Toast.LENGTH_SHORT).show()
}



















