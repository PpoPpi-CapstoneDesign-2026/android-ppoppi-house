package com.ppoppi.house.ui.diagnosis.camera.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import com.ppoppi.house.ui.theme.Primary400
import com.ppoppi.house.ui.theme.White

@Composable
fun CameraGridOverlay(modifier: Modifier = Modifier) {
    val gridColor = White.copy(alpha = 0.35f)
    val cornerColor = Primary400
    val squareSize = 152.dp
    val cornerLineLength = 48.dp
    val strokeWidth = 1.5.dp

    Canvas(modifier = modifier.fillMaxSize()) {
        val centerX = size.width / 2f
        val centerY = size.height / 2f
        val half = squareSize.toPx() / 2f
        val cornerPx = cornerLineLength.toPx()
        val strokePx = strokeWidth.toPx()

        val left = centerX - half
        val top = centerY - half
        val right = centerX + half
        val bottom = centerY + half

        drawLine(gridColor, Offset(left, 0f), Offset(left, size.height), strokePx)
        drawLine(gridColor, Offset(right, 0f), Offset(right, size.height), strokePx)

        drawLine(gridColor, Offset(0f, top), Offset(size.width, top), strokePx)
        drawLine(gridColor, Offset(0f, bottom), Offset(size.width, bottom), strokePx)

        val cornerStroke = strokePx * 2f

        drawLine(cornerColor, Offset(left, top), Offset(left + cornerPx, top), cornerStroke)
        drawLine(cornerColor, Offset(left, top), Offset(left, top + cornerPx), cornerStroke)

        drawLine(cornerColor, Offset(right, top), Offset(right - cornerPx, top), cornerStroke)
        drawLine(cornerColor, Offset(right, top), Offset(right, top + cornerPx), cornerStroke)

        drawLine(cornerColor, Offset(left, bottom), Offset(left + cornerPx, bottom), cornerStroke)
        drawLine(cornerColor, Offset(left, bottom), Offset(left, bottom - cornerPx), cornerStroke)

        drawLine(cornerColor, Offset(right, bottom), Offset(right - cornerPx, bottom), cornerStroke)
        drawLine(cornerColor, Offset(right, bottom), Offset(right, bottom - cornerPx), cornerStroke)

        drawCircle(
            color = cornerColor,
            radius = 6.dp.toPx(),
            center = Offset(centerX, centerY),
        )
    }
}
