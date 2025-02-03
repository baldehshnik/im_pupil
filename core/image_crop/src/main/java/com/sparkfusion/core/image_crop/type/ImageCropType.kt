package com.sparkfusion.core.image_crop.type

import androidx.compose.ui.unit.Dp
import com.sparkfusion.core.image_crop.common.DpSerializer
import kotlinx.serialization.Serializable

@Serializable
sealed class ImageCropType {

    @Serializable
    data object CircleCrop : ImageCropType()

    @Serializable
    data object RectangleCrop : ImageCropType()

    @Serializable
    data class DynamicRectangleCrop(
        @Serializable(with = DpSerializer::class) val widthDp: Dp,
        @Serializable(with = DpSerializer::class) val heightDp: Dp
    ) : ImageCropType()
}