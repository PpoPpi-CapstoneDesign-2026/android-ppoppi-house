package com.ppoppi.house.ui.main.home.component

import android.graphics.BlurMaskFilter
import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.Gray100
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.Primary400
import com.ppoppi.house.ui.theme.White

@Composable
fun ToDiagnosisCard(
    onClick: () -> Unit,
    name: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .drawBehind {
                    drawIntoCanvas { canvas ->
                        val paint = Paint()
                        paint.asFrameworkPaint().apply {
                            isAntiAlias = true
                            color = Color.argb(64, 0, 0, 0)
                            maskFilter = BlurMaskFilter(2.dp.toPx(), BlurMaskFilter.Blur.NORMAL)
                        }
                        canvas.drawRoundRect(
                            left = 0f,
                            top = 2.dp.toPx(),
                            right = size.width,
                            bottom = size.height,
                            radiusX = 8.dp.toPx(),
                            radiusY = 8.dp.toPx(),
                            paint = paint,
                        )
                    }
                }.background(White, RoundedCornerShape(8.dp))
                .border(1.dp, Gray100, RoundedCornerShape(8.dp))
                .padding(bottom = 16.dp, top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "📸",
            fontSize = 64.sp,
        )

        Text(
            text = "사진 한 장으로 $name 눈 건강 체크!",
            style = PpoPpiTheme.typography.title2,
            color = Black,
            modifier = Modifier.padding(top = 30.dp),
        )

        Button(
            onClick = onClick,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 10.dp),
            shape = RoundedCornerShape(8.dp),
            colors =
                ButtonDefaults.buttonColors(
                    containerColor = Primary400,
                ),
        ) {
            Text(
                text = "진단하러 가기",
                style = PpoPpiTheme.typography.title3,
                color = White,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ToDiagnosisCardPreview() {
    PpoPpiTheme {
        ToDiagnosisCard(
            onClick = {},
            name = "뽀삐",
        )
    }
}
