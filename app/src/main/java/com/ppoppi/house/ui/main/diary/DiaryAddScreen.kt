package com.ppoppi.house.ui.main.diary

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ppoppi.house.domain.model.HealthChecklist
import com.ppoppi.house.ui.component.BottomBarButton
import com.ppoppi.house.ui.main.diary.component.ChecklistItem
import com.ppoppi.house.ui.main.diary.component.DiaryAddTopBar
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.Gray200
import com.ppoppi.house.ui.theme.Gray300
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.Primary200
import com.ppoppi.house.ui.theme.Primary400
import com.ppoppi.house.ui.theme.White
import com.ppoppi.house.ui.util.getColoredText

@Composable
fun DiaryAddScreen(
    onBackClick: () -> Unit,
    onSaveSuccess: (petId: Long, petName: String, memo: String, selectedChecklist: List<HealthChecklist>) -> Unit,
    petId: Long,
    petName: String,
    modifier: Modifier = Modifier,
    viewModel: DiaryAddViewModel = hiltViewModel(),
) {
    val healthChecklist by viewModel.healthChecklists.collectAsState()
    var memo by remember { mutableStateOf("") }
    var checklist: Set<Long> by remember { mutableStateOf(setOf()) }

    LaunchedEffect(Unit) {
        viewModel.savedEvent.collect {
            onSaveSuccess(petId, petName, memo, healthChecklist.filter { it.id in checklist })
        }
    }

    Scaffold(
        topBar = { DiaryAddTopBar(onBackClick) },
        modifier = modifier.background(White),
        containerColor = White,
        bottomBar = {
            BottomBarButton(
                onClick = {
                    viewModel.postDiary(petId, memo, checklist.toList())
                },
                text = "다이어리 저장",
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp),
        ) {
            Text(
                text = "${petName}의 다이어리를 추가해 보세요".getColoredText(Primary400, petName),
                style = PpoPpiTheme.typography.title1,
                color = Black,
                modifier = Modifier.padding(top = 24.dp),
            )

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
                    val isChecked = item.id in checklist
                    ChecklistItem(
                        checklist = item.description,
                        onClick = {
                            checklist =
                                if (isChecked) checklist - item.id else checklist + item.id
                        },
                        backgroundColor = if (isChecked) Primary200 else White,
                        borderColor = if (isChecked) Primary200 else Gray200,
                        textColor = if (isChecked) White else Gray200,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DiaryAddScreenPreview() {
    PpoPpiTheme {
        DiaryAddScreen(onBackClick = {}, onSaveSuccess = { _, _, _, _ -> }, petId = 1L, petName = "뽀삐")
    }
}
