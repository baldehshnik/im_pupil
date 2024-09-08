package com.sparkfusion.features.admin.post.entity

import androidx.annotation.DrawableRes

data class AdditionalBlockEntity(
    @DrawableRes val iconId: Int,
    val title: String,
    val contentDescription: String
)
