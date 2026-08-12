package com.ppoppi.house.ui.diagnosis.result.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.Gray200
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.Primary100
import com.ppoppi.house.ui.theme.Primary400

@Composable
fun LoadingContent(
    name: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        LoadingSpinner()

        Text(
            text = "${name}의 눈을 분석하고 있어요.",
            style = PpoPpiTheme.typography.title2,
            color = Black,
            modifier = Modifier.padding(top = 30.dp),
        )

        Text(
            text = "AI 모델이 안구 이미지와\n증상을 함께 분석중입니다",
            style = PpoPpiTheme.typography.body3,
            color = Gray200,
            modifier = Modifier.padding(top = 14.dp),
        )

        Spacer(modifier = Modifier.height(80.dp))
    }
}

@Composable
private fun LoadingSpinner(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition(label = "spinner")
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec =
            infiniteRepeatable(
                animation = tween(durationMillis = 1000, easing = LinearEasing),
            ),
        label = "angle",
    )

    Canvas(modifier = modifier.size(90.dp)) {
        val strokeWidth = 4.dp.toPx()
        val inset = strokeWidth / 2
        val arcSize = Size(size.width - strokeWidth, size.height - strokeWidth)
        val topLeft = Offset(inset, inset)

        drawArc(
            color = Primary100,
            startAngle = 0f,
            sweepAngle = 360f,
            useCenter = false,
            topLeft = topLeft,
            size = arcSize,
            style = Stroke(width = strokeWidth),
        )

        drawArc(
            color = Primary400,
            startAngle = angle,
            sweepAngle = 270f,
            useCenter = false,
            topLeft = topLeft,
            size = arcSize,
            style = Stroke(width = strokeWidth, cap = StrokeCap.Round),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingContentPreview() {
    PpoPpiTheme {
        LoadingContent(name = "뽀삐")
    }
}
