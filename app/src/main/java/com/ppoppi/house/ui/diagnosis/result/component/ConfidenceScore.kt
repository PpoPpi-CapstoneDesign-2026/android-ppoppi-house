package com.ppoppi.house.ui.diagnosis.result.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.ui.theme.Amber200
import com.ppoppi.house.ui.theme.Gray100
import com.ppoppi.house.ui.theme.Gray200
import com.ppoppi.house.ui.theme.PpoPpiTheme

@Composable
fun ConfidenceScore(
    confidenceScore: Int,
    triageColor: Color,
    modifier: Modifier = Modifier,
) {
    val progress =
        (confidenceScore / 100.0)
            .coerceIn(0.0, 1.0)
            .toFloat()

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "신뢰도",
            style = PpoPpiTheme.typography.label1,
            color = Gray200,
        )

        Spacer(modifier = Modifier.width(10.dp))
        Box(
            modifier =
                Modifier
                    .weight(1f)
                    .height(4.dp)
                    .background(Gray100, RoundedCornerShape(100.dp))
                    .clip(RoundedCornerShape(100.dp)),
        ) {
            Canvas(modifier = Modifier.matchParentSize()) {
                val filledWidth = size.width * progress
                val cornerRadiusPx = minOf(100.dp.toPx(), size.height / 2f)

                if (filledWidth > 0f) {
                    drawRoundRect(
                        color = triageColor,
                        size = Size(filledWidth, size.height),
                        cornerRadius =
                            CornerRadius(
                                cornerRadiusPx,
                                cornerRadiusPx,
                            ),
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "$confidenceScore%",
            style = PpoPpiTheme.typography.label1,
            color = triageColor,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ConfidenceScorePreview() {
    PpoPpiTheme {
        ConfidenceScore(
            confidenceScore = 70,
            triageColor = Amber200,
        )
    }
}
