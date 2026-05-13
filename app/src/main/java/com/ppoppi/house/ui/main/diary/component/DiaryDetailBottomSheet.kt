package com.ppoppi.house.ui.main.diary.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.domain.model.HealthChecklist
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.Gray200
import com.ppoppi.house.ui.theme.Gray300
import com.ppoppi.house.ui.theme.Gray400
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.Primary800
import com.ppoppi.house.ui.util.getColoredText
import com.ppoppi.house.ui.util.noRippleClickable

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DiaryDetailBottomSheet(
    onDismiss: () -> Unit,
    petId: Long,
    petName: String,
    memo: String,
    healthChecklist: List<HealthChecklist>,
    modifier: Modifier = Modifier,
) {
    var memo by remember { mutableStateOf(memo) }

    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "$petName 다이어리 수정".getColoredText(Primary800, petName),
                style = PpoPpiTheme.typography.title1,
                color = Black,
            )

            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                modifier =
                    Modifier
                        .size(40.dp)
                        .noRippleClickable(onClick = onDismiss)
                        .padding(8.dp),
                tint = Gray400,
            )
        }

        Text(
            text = "메모",
            style = PpoPpiTheme.typography.title3,
            color = Black,
            modifier = Modifier.padding(top = 28.dp),
        )

        Box(
            modifier =
                Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .height(94.dp)
                    .border(1.dp, Gray300, RoundedCornerShape(8.dp))
                    .padding(10.dp),
        ) {
            BasicTextField(
                value = memo,
                onValueChange = { newValue ->
                    if (newValue.length < 500) memo = newValue
                },
                textStyle = PpoPpiTheme.typography.body3.copy(color = Black),
                modifier = Modifier.fillMaxSize(),
                decorationBox = { innerTextField ->
                    Box(modifier = Modifier.fillMaxSize()) {
                        innerTextField()
                        Text(
                            text = "${memo.length} / 500",
                            style = PpoPpiTheme.typography.label1,
                            color = Gray200,
                            modifier = Modifier.align(Alignment.BottomEnd),
                        )
                    }
                },
            )
        }

        Text(
            text = "건강 체크리스트",
            style = PpoPpiTheme.typography.title3,
            color = Black,
            modifier = Modifier.padding(top = 28.dp),
        )

        FlowRow(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            healthChecklist.forEach { item ->
                ChecklistItem(checklist = item.description)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DiaryDetailBottomSheetPreview() {
    PpoPpiTheme {
        DiaryDetailBottomSheet(
            onDismiss = {},
            petId = 1,
            petName = "뽀삐",
            memo = "뽀삐의 메모",
            healthChecklist =
                listOf(
                    HealthChecklist(id = 1, description = "식욕 없음"),
                    HealthChecklist(id = 2, description = "활동량 적음"),
                ),
        )
    }
}
