package com.ppoppi.house.ui.main.diary.component

import android.graphics.BlurMaskFilter
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
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
import coil.compose.AsyncImage
import com.ppoppi.house.domain.COLOR
import com.ppoppi.house.domain.COLOR.Companion.toColor
import com.ppoppi.house.domain.Diagnosis
import com.ppoppi.house.domain.Pet
import com.ppoppi.house.domain.SEX
import com.ppoppi.house.domain.SPECIES
import com.ppoppi.house.domain.Triage
import com.ppoppi.house.domain.Triage.Companion.toColor
import com.ppoppi.house.ui.diagnosis.result.component.TriageBadge
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.Gray100
import com.ppoppi.house.ui.theme.Gray300
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.Primary800
import com.ppoppi.house.ui.theme.White

@Composable
fun DiaryItem(
    pet: Pet,
    diagnosis: Diagnosis?,
    checklist: List<String>,
    memo: String?,
    healthChecklist: List<String>,
    modifier: Modifier = Modifier,
) {
    val petColor = pet.color.toColor()

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
                }.clip(RoundedCornerShape(8.dp))
                .background(White)
                .drawBehind {
                    drawRect(
                        color = petColor,
                        topLeft = Offset(0f, 0f),
                        size = Size(4.dp.toPx(), size.height),
                    )
                }.padding(16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier =
                    Modifier
                        .size(10.dp)
                        .background(petColor, RoundedCornerShape(100.dp)),
            )
            Text(
                text = pet.name,
                style = PpoPpiTheme.typography.title2,
                color = petColor,
                modifier = Modifier.padding(start = 6.dp),
            )
        }

        HorizontalDivider(
            modifier = Modifier.padding(top = 10.dp),
            thickness = 1.dp,
            color = Gray100,
        )

        // 진단 결과
        if (diagnosis != null) {
            val (backgroundColor, borderColor, textColor) = diagnosis.triageKey.toColor()
            Text(
                text = "🔍진단 결과",
                style = PpoPpiTheme.typography.title3,
                color = Primary800,
                modifier = Modifier.padding(top = 10.dp),
            )

            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 14.dp)
                        .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val imageUrl = diagnosis.imageUrl ?: return@Column
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    modifier =
                        Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Black, RoundedCornerShape(8.dp)),
                )

                Column(
                    modifier = Modifier.padding(start = 10.dp),
                ) {
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(14.dp),
                    ) {
                        Text(
                            text = diagnosis.guideTitle,
                            style = PpoPpiTheme.typography.body1,
                            color = Black,
                        )
                        TriageBadge(triage = diagnosis.triageKey)
                    }
                    Row(
                        modifier = Modifier.padding(top = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "신뢰도",
                            style = PpoPpiTheme.typography.label1,
                            color = Gray300,
                        )
                        Text(
                            text = "${diagnosis.triageConfidence.toInt()}%",
                            style = PpoPpiTheme.typography.label1,
                            color = diagnosis.triageKey.toColor()[1],
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            FlowRow(
                modifier = Modifier.padding(top = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                checklist.forEach { description ->
                    ChecklistItem(
                        checklist = description,
                        backgroundColor = backgroundColor,
                        textColor = textColor,
                        borderColor = borderColor,
                    )
                }
            }

            if (memo?.isNotEmpty() == true || healthChecklist.isNotEmpty()) {
                HorizontalDivider(
                    modifier = Modifier.padding(top = 10.dp),
                    thickness = 1.dp,
                    color = Gray100,
                )
            }
        }

        // 메모
        if (memo != null && memo.isNotEmpty()) {
            Text(
                text = "📝메모",
                style = PpoPpiTheme.typography.title3,
                color = Primary800,
                modifier = Modifier.padding(top = 10.dp),
            )

            Text(
                text = memo,
                style = PpoPpiTheme.typography.body5,
                color = Black,
                modifier = Modifier.padding(top = 10.dp),
            )

            if (healthChecklist.isNotEmpty()) {
                HorizontalDivider(
                    modifier = Modifier.padding(top = 10.dp),
                    thickness = 1.dp,
                    color = Gray100,
                )
            }
        }

        // 건강 체크리스트
        FlowRow(
            modifier = Modifier.padding(top = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            healthChecklist.forEach {
                ChecklistItem(
                    checklist = it,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DiaryItemPreview() {
    PpoPpiTheme {
        DiaryItem(
            pet =
                Pet(
                    name = "뽀삐",
                    species = SPECIES.DOG,
                    breed = "뽀삐",
                    age = 1,
                    sex = SEX.MALE,
                    color = COLOR.PRIMARY600,
                ),
            diagnosis =
                Diagnosis(
                    guideTitle = "결막염",
                    triageKey = Triage.URGENT,
                    triageConfidence = 80.0,
                    affectedArea = "각막",
                    guideMsg = "어쩌구저쩌구",
                    guideAction = "어쩌구저쩌구",
                    imageUrl = "https://picsum.photos/400/400",
                ),
            checklist = listOf("눈물", "눈곱"),
            memo = "메모입니다?메모입니다?메모입니다?메모입니다?메모입니다?메모입니다?메모입니다?메모입니다?메모입니다?메모입니다?메모입니다?\n메모입니다?메모입니다?",
            healthChecklist = listOf("식욕 없음", "활동량 적음", "활동량 적음", "활동량 적음", "활동량 적음"),
        )
    }
}
