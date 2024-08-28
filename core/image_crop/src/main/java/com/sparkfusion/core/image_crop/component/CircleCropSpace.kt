package com.sparkfusion.core.image_crop.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawTransform
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset

@Composable
fun CircleCropSpace(
    modifier: Modifier,
    cropCircleDiameter: Float,
    image: ImageBitmap,
    onScaleChange: ((Float) -> Unit)? = null,
    onTransformChange: ((Offset) -> Unit)? = null
) {
    // Определяем минимальный масштаб, основываясь на меньшей стороне изображения
    val minScale = calculateMinScale(image.width, image.height, cropCircleDiameter)
    var scale by remember { mutableFloatStateOf(1f) }
    var transform by remember { mutableStateOf(Offset(0f, 0f)) }
    val circleRadius = cropCircleDiameter / 2

    Box(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTransformGestures { _, pan, zoom, _ ->
                    // Обновляем масштаб с учетом минимального масштаба
                    scale = (scale * zoom).coerceIn(minScale, 10f)
                    transform = transformHandlerBlock(transform, pan, scale, image, cropCircleDiameter)
                    onScaleChange?.invoke(scale)
                    onTransformChange?.invoke(transform)
                }
            }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            withTransform(
                transformBlock = { transformBlock(transform, scale, size) },
                drawBlock = { drawBlock(image, size) }
            )
            drawBackgroundBlock(circleRadius, size)
        }
    }
}

private fun calculateMinScale(imageWidth: Int, imageHeight: Int, cropCircleDiameter: Float): Float {
    // Определяем минимальный масштаб, чтобы изображение не было меньше чем минимальная сторона обрезки
    val minSide = minOf(imageWidth.toFloat(), imageHeight.toFloat())
    return cropCircleDiameter / minSide
}

private fun transformHandlerBlock(
    transform: Offset,
    pan: Offset,
    scale: Float,
    image: ImageBitmap,
    cropCircleDiameter: Float
): Offset {
    val scaledWidth = image.width * scale
    val scaledHeight = image.height * scale

    // Корректировка ограничений для трансформации
    val transformXMax = (scaledWidth - cropCircleDiameter).coerceAtLeast(0f) / 2
    val transformYMax = (scaledHeight - cropCircleDiameter).coerceAtLeast(0f) / 2
    val transformXMin = -transformXMax
    val transformYMin = -transformYMax

    return Offset(
        (transform.x + pan.x).coerceIn(transformXMin, transformXMax),
        (transform.y + pan.y).coerceIn(transformYMin, transformYMax)
    )
}

//@Composable
//fun CircleCropSpace(
//    modifier: Modifier,
//    cropCircleDiameter: Float,
//    image: ImageBitmap,
//    onScaleChange: ((Float) -> Unit)? = null,
//    onTransformChange: ((Offset) -> Unit)? = null
//) {
//    var scale by remember { mutableFloatStateOf(1f) }
//    var transform by remember { mutableStateOf(Offset(0f, 0f)) }
//    val circleRadius = cropCircleDiameter / 2
//
//    Box(
//        modifier = modifier
//            .fillMaxSize()
//            .pointerInput(Unit) {
//                detectTransformGestures { _, pan, zoom, _ ->
//                    scale = (scale * zoom).coerceIn(1f, 10f)
//                    transform = transformHandlerBlock(transform, pan, scale, image, cropCircleDiameter)
//                    onScaleChange?.invoke(scale)
//                    onTransformChange?.invoke(transform)
//                }
//            }
//    ) {
//        Canvas(modifier = Modifier.fillMaxSize()) {
//            withTransform(
//                transformBlock = { transformBlock(transform, scale, size) },
//                drawBlock = { drawBlock(image, size) }
//            )
//            drawBackgroundBlock(circleRadius, size)
//        }
//    }
//}
//
//private fun transformHandlerBlock(
//    transform: Offset,
//    pan: Offset,
//    scale: Float,
//    image: ImageBitmap,
//    cropCircleDiameter: Float
//): Offset {
//    val scaledWidth = image.width * scale
//    val scaledHeight = image.height * scale
//
//    val transformXMax = (scaledWidth - cropCircleDiameter).coerceAtLeast(0f) / 2
//    val transformYMax = (scaledHeight - cropCircleDiameter).coerceAtLeast(0f) / 2
//    val transformXMin = -transformXMax
//    val transformYMin = -transformYMax
//
//    return Offset(
//        (transform.x + pan.x).coerceIn(transformXMin, transformXMax),
//        (transform.y + pan.y).coerceIn(transformYMin, transformYMax)
//    )
//}

private fun DrawScope.drawBackgroundBlock(
    circleRadius: Float,
    canvas: Size
) {
    val nativeCanvas = drawContext.canvas.nativeCanvas
    val checkPoint = nativeCanvas.saveLayer(null, null)

    drawRect(Color(0x75000000))
    drawCircle(
        color = Color.Transparent,
        radius = circleRadius,
        center = Offset(
            x = canvas.width / 2,
            y = canvas.height / 2
        ),
        blendMode = BlendMode.Clear
    )

    nativeCanvas.restoreToCount(checkPoint)
}

private fun DrawScope.drawBlock(
    image: ImageBitmap,
    canvas: Size
) {
    drawImage(
        image = image,
        dstOffset = IntOffset(
            x = canvas.width.div(2).minus(image.width.div(2)).toInt(),
            y = canvas.height.div(2).minus(image.height.div(2)).toInt()
        )
    )
}

private fun DrawTransform.transformBlock(
    transform: Offset,
    scale: Float,
    canvas: Size
) {
    translate(
        left = transform.x,
        top = transform.y
    )
    scale(
        scaleX = scale,
        scaleY = scale,
        pivot = Offset(
            canvas.width.div(2),
            canvas.height.div(2)
        )
    )
}