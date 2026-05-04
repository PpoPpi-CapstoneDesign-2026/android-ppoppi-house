package com.ppoppi.house.ui.diagnosis.result.component

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.ppoppi.house.domain.model.Diagnosis
import com.ppoppi.house.domain.model.Triage
import com.ppoppi.house.domain.model.Triage.Companion.toColor
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.Gray100
import com.ppoppi.house.ui.theme.Gray400
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.White
import com.ppoppi.house.ui.util.getColoredText

@Composable
fun ResultCard(
    diagnosis: Diagnosis,
    modifier: Modifier = Modifier,
) {
    val textColor = diagnosis.triage.toColor()[1]
    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .drawBehind {
                    drawIntoCanvas { canvas ->
                        val paint =
                            Paint().apply {
                                asFrameworkPaint().apply {
                                    isAntiAlias = true
                                    color = Black.copy(alpha = 0.25f).toArgb()
                                    maskFilter =
                                        BlurMaskFilter(4.dp.toPx(), BlurMaskFilter.Blur.NORMAL)
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
                        color = textColor,
                        topLeft = Offset(0f, 0f),
                        size = Size(4.dp.toPx(), size.height),
                    )
                }
                .padding(20.dp),
    ) {
        val highlightColor = diagnosis.triage.toColor()[2]
        val title = if (diagnosis.triage == Triage.NORMAL) "정상 안구입니다".getColoredText(
            highlightColor,
            "정상 안구"
        ) else "${diagnosis.diseaseName}이 의심됩니다.".getColoredText(
            highlightColor,
            diagnosis.diseaseName
        )
        val action =
            if (diagnosis.triage == Triage.URGENT || diagnosis.triage == Triage.SOON) "${diagnosis.guideAction} 내원 권장".getColoredText(
                highlightColor,
                diagnosis.guideAction
            ) else diagnosis.guideAction.getColoredText(
                highlightColor,
                diagnosis.guideAction
            )

        Text(
            text = title,
            style = PpoPpiTheme.typography.title1,
            color = Black,
        )
        Text(
            text = diagnosis.guideMessage,
            style = PpoPpiTheme.typography.body1,
            color = Gray400,
        )

        if (diagnosis.triage != Triage.NORMAL) {
            HorizontalDivider(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                thickness = 1.dp,
                color = Gray100,
            )
            Text(
                text = action,
                style = PpoPpiTheme.typography.title1,
                color = Black,
                modifier = Modifier.padding(top = 12.dp),
            )
            Text(
                text = diagnosis.guideWarning,
                style = PpoPpiTheme.typography.body1,
                color = Gray400,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ResultCardPreview() {
    PpoPpiTheme {
        ResultCard(
            diagnosis =
                Diagnosis(
                    triage = Triage.URGENT,
                    triageConfidence = 70,
                    affectedArea = "각막",
                    guideMessage = "결막염이 의심 어쩌구",
                    guideAction = "액션 어쩌구",
                    imageUrl = null,
                    hasDiagnosis = true,
                    diseaseName = "결막염",
                    symptoms = emptyList(),
                    guideWarning = "asdfasdf",
                ),
        )
    }
}
