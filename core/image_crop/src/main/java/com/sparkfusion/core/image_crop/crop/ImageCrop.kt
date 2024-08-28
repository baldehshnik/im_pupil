package com.sparkfusion.core.image_crop.crop

import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap

class ImageCrop(private val targetBitmap: ImageBitmap) {

    fun cropImage(cropSize: Float, transform: Offset, scale: Float): Bitmap {
        val scaledBitmap = createScaledBitmap(scale)
        val (cropX, cropY) = getCropCoordinates(scaledBitmap, cropSize, transform)
        val cropDiameter = cropSize.toInt()

        val croppedBitmap = Bitmap.createBitmap(cropDiameter, cropDiameter, Bitmap.Config.ARGB_8888)
        val canvas = android.graphics.Canvas(croppedBitmap)
        val paint = Paint().apply {
            isAntiAlias = true
        }

        val halfOfDiameter = cropDiameter / 2f
        canvas.drawCircle(halfOfDiameter, halfOfDiameter, halfOfDiameter, paint)

        val (srcRect, destRect) = getRectangles(cropDiameter, cropX, cropY)
        val maskPaint = Paint().apply { xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN) }
        canvas.drawBitmap(scaledBitmap, srcRect, destRect, maskPaint)

        return croppedBitmap
    }

    private fun getCropCoordinates(
        scaledBitmap: Bitmap, cropSize: Float, transform: Offset
    ): Pair<Int, Int> {
        val cropRadius = cropSize / 2
        val cropX = (scaledBitmap.width / 2 - cropRadius - transform.x).toInt()
        val cropY = (scaledBitmap.height / 2 - cropRadius - transform.y).toInt()

        return Pair(cropX, cropY)
    }

    private fun getRectangles(cropDiameter: Int, cropX: Int, cropY: Int): Pair<Rect, Rect> {
        val srcRect = Rect(cropX, cropY, cropX + cropDiameter, cropY + cropDiameter)
        val destRect = Rect(0, 0, cropDiameter, cropDiameter)

        return Pair(srcRect, destRect)
    }

    private fun createScaledBitmap(scale: Float): Bitmap {
        val matrix = Matrix().apply { postScale(scale, scale) }
        return Bitmap.createBitmap(
            targetBitmap.asAndroidBitmap(),
            0, 0,
            targetBitmap.width,
            targetBitmap.height,
            matrix, true
        )
    }
}