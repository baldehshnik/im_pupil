package com.sparkfusion.core.image_crop.type

sealed class ImageCropType {
    data object CircleCrop : ImageCropType()
    data object RectangleCrop : ImageCropType()
}