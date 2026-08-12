package com.ppoppi.house.ui.main.home.component

import android.graphics.BlurMaskFilter
import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.domain.model.Disease
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.Gray100
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.Primary50
import com.ppoppi.house.ui.theme.Primary600
import com.ppoppi.house.ui.theme.White

@Composable
fun DiseaseCard(
    disease: Disease,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .height(160.dp)
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
                .padding(vertical = 16.dp, horizontal = 22.dp),
    ) {
        Text(
            text = "🏥   ${disease.name}",
            style = PpoPpiTheme.typography.headline1,
            color = Black,
            textAlign = TextAlign.Center,
        )

        BreedLabel(breed = disease.breed, modifier = Modifier.padding(top = 10.dp))

        Text(
            text = disease.description,
            style = PpoPpiTheme.typography.body3,
            color = Black,
            modifier = Modifier.padding(top = 16.dp),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DiseaseCardPreview() {
    PpoPpiTheme {
        DiseaseCard(
            disease =
                Disease(
                    diseaseId = 1,
                    name = "결막염",
                    breed = "무슨 종류",
                    description = "결막염은 어쩌구 저쩌구 ㄴㅇㄹ먖ㄷ결막염은 어쩌구 저쩌구 ㄴㅇㄹ먖ㄷ결막염은 어쩌구 저쩌구 ㄴㅇㄹ먖ㄷ",
                ),
        )
    }
}

@Composable
private fun BreedLabel(
    breed: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier =
            modifier
                .clip(RoundedCornerShape(100.dp))
                .background(Primary50)
                .border(1.dp, Primary600, RoundedCornerShape(100.dp))
                .padding(vertical = 4.dp, horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = breed,
            style = PpoPpiTheme.typography.body3,
            color = Primary600,
        )
    }
}
