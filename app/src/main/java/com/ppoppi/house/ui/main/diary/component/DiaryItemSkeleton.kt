package com.ppoppi.house.ui.main.diary.component

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.ui.component.shimmerBrush
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.Gray100
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.White

@Composable
fun DiaryItemSkeleton(modifier: Modifier = Modifier) {
    val brush = shimmerBrush()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .drawBehind {
                drawIntoCanvas { canvas ->
                    val paint = Paint().apply {
                        asFrameworkPaint().apply {
                            isAntiAlias = true
                            color = Black.copy(alpha = 0.25f).toArgb()
                            maskFilter = BlurMaskFilter(4.dp.toPx(), BlurMaskFilter.Blur.NORMAL)
                        }
                    }
                    canvas.drawRoundRect(
                        left = 0f,
                        top = 4.dp.toPx(),
                        right = size.width,
                        bottom = size.height + 4.dp.toPx(),
                        radiusX = 8.dp.toPx(),
                        radiusY = 8.dp.toPx(),
                        paint = paint,
                    )
                }
            }
            .clip(RoundedCornerShape(8.dp))
            .background(White)
            .drawBehind {
                drawRect(
                    color = Gray100,
                    topLeft = Offset(0f, 0f),
                    size = Size(4.dp.toPx(), size.height),
                )
            }
            .padding(16.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .background(brush, CircleShape),
            )
            Box(
                modifier = Modifier
                    .padding(start = 6.dp)
                    .size(80.dp, 16.dp)
                    .background(brush, RoundedCornerShape(4.dp)),
            )
        }

        HorizontalDivider(
            modifier = Modifier.padding(top = 10.dp),
            thickness = 1.dp,
            color = Gray100,
        )

        Box(
            modifier = Modifier
                .padding(top = 10.dp)
                .size(60.dp, 14.dp)
                .background(brush, RoundedCornerShape(4.dp)),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(brush, RoundedCornerShape(8.dp)),
            )
            Column(modifier = Modifier.padding(start = 10.dp)) {
                Box(
                    modifier = Modifier
                        .size(100.dp, 16.dp)
                        .background(brush, RoundedCornerShape(4.dp)),
                )
                Box(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .size(70.dp, 12.dp)
                        .background(brush, RoundedCornerShape(4.dp)),
                )
            }
        }

        Row(
            modifier = Modifier.padding(top = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            repeat(3) {
                Box(
                    modifier = Modifier
                        .size(60.dp, 28.dp)
                        .background(brush, RoundedCornerShape(100.dp)),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DiaryItemSkeletonPreview() {
    PpoPpiTheme {
        DiaryItemSkeleton()
    }
}
