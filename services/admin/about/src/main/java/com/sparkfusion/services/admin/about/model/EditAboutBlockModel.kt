package com.sparkfusion.services.admin.about.model

import android.graphics.Bitmap

internal data class EditAboutBlockModel(
    val id: Int?,
    val description: String?,
    val icon: String?,
    val bitmap: Bitmap? = null
)
