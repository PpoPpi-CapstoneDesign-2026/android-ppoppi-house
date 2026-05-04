package com.ppoppi.house.ui.main.home.component

import android.graphics.BlurMaskFilter
import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ppoppi.house.domain.model.Diagnosis
import com.ppoppi.house.domain.model.Symptom
import com.ppoppi.house.domain.model.Triage
import com.ppoppi.house.domain.model.Triage.Companion.toColor
import com.ppoppi.house.ui.diagnosis.result.component.TriageBadge
import com.ppoppi.house.ui.main.diary.component.ChecklistItem
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.Gray100
import com.ppoppi.house.ui.theme.Gray400
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.Primary400
import com.ppoppi.house.ui.theme.White

@Composable
fun DiagnosisCard(
    diagnosis: Diagnosis,
    modifier: Modifier = Modifier,
) {
    val (backgroundColor, borderColor, textColor) = diagnosis.triage.toColor()

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
                .padding(vertical = 16.dp, horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = diagnosis.imageUrl,
            contentDescription = null,
            modifier =
                Modifier
                    .size(160.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Black, RoundedCornerShape(8.dp)),
        )
        TriageBadge(triage = diagnosis.triage, modifier = Modifier.padding(top = 20.dp))
        Text(
            text = "${diagnosis.diseaseName} | ${diagnosis.affectedArea}",
            style = PpoPpiTheme.typography.title2,
            color = Black,
            modifier = Modifier.padding(top = 6.dp),
        )

        ConfidenceScore(
            confidenceScore = diagnosis.triageConfidence,
            triageColor = diagnosis.triage.toColor()[1],
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
        )

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            diagnosis.symptoms?.forEach { symptom ->
                ChecklistItem(
                    checklist = symptom.description,
                    backgroundColor = backgroundColor,
                    borderColor = borderColor,
                    textColor = textColor,
                )
            }
        }

        Text(
            text = diagnosis.guideMessage,
            style = PpoPpiTheme.typography.body3,
            color = Gray400,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
        )

        Button(
            onClick = { },
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
            shape = RoundedCornerShape(8.dp),
            colors =
                ButtonDefaults.buttonColors(
                    containerColor = Primary400,
                ),
            contentPadding = PaddingValues(vertical = 12.dp),
        ) {
            Text(
                text = "다시 진단하기",
                style = PpoPpiTheme.typography.title3,
                color = White,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DiagnosisCardPreview() {
    PpoPpiTheme {
        DiagnosisCard(
            diagnosis =
                Diagnosis(
                    hasDiagnosis = true,
                    imageUrl = null,
                    triage = Triage.SOON,
                    diseaseName = "결막염",
                    affectedArea = "각막",
                    triageConfidence = 85,
                    guideAction = "동물병원 방문 권장",
                    guideMessage =
                        "방치 시 각막염으로 진행될 수 있습니다.\n" +
                            "조기 치료가 중요합니다.",
                    guideWarning = "결막염",
                    symptoms =
                        listOf(
                            Symptom(
                                id = 1L,
                                description = "충혈",
                            ),
                            Symptom(
                                id = 1L,
                                description = "눈물",
                            ),
                        ),
                ),
        )
    }
}
