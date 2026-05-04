package com.ppoppi.house.ui.diagnosis.check

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ppoppi.house.R
import com.ppoppi.house.ui.component.BottomBarButton
import com.ppoppi.house.ui.diagnosis.check.model.SymptomItem
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.Gray100
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.Primary400
import com.ppoppi.house.ui.theme.White
import com.ppoppi.house.ui.util.getStyledText
import com.ppoppi.house.ui.util.noRippleClickable
import kotlin.collections.filter
import kotlin.collections.toMutableList

@Composable
fun ChecklistScreen(
    onBackClick: () -> Unit,
    onComplete: (List<Int>) -> Unit,
    viewModel: ChecklistViewModel = hiltViewModel(),
) {
    val symptoms by viewModel.symptoms.collectAsState()
    var selectedSymptoms: List<SymptomItem> by remember(symptoms) { mutableStateOf(symptoms) }

    Scaffold(
        topBar = { ChecklistTopAppBar(onBackClick) },
        containerColor = White,
        bottomBar = {
            BottomBarButton(
                onClick = {
                    onComplete(selectedSymptoms.filter { it.isChecked }.map { it.id })
                },
                text = stringResource(R.string.diagnosis_check_bottom_bar_button),
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 22.dp),
        ) {
            Text(
                text =
                    stringResource(R.string.diagnosis_check_hint).getStyledText(
                        style = PpoPpiTheme.typography.title2,
                        stringResource(R.string.diagnosis_check_hint_highlight),
                    ),
                style = PpoPpiTheme.typography.body2,
                color = Black,
                modifier = Modifier.padding(top = 20.dp),
            )

            LazyColumn(
                modifier = Modifier.padding(top = 26.dp),
            ) {
                items(selectedSymptoms.size) {
                    ChecklistItem(
                        description = selectedSymptoms[it].description,
                        isChecked = selectedSymptoms[it].isChecked,
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp)
                                .noRippleClickable(onClick = {
                                    selectedSymptoms =
                                        selectedSymptoms.toMutableList().also { list ->
                                            list[it] =
                                                list[it].copy(isChecked = !list[it].isChecked)
                                        }
                                }),
                    )
                }
            }
        }
    }
}

@Composable
fun ChecklistItem(
    description: String,
    isChecked: Boolean,
    modifier: Modifier = Modifier,
) {
    val textColor = if (isChecked) White else Black
    val backgroundColor = if (isChecked) Primary400 else White
    val borderColor = if (isChecked) Primary400 else Gray100
    Box(
        modifier =
            modifier
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(12.dp),
                ).background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(12.dp),
                ).padding(vertical = 20.dp),
    ) {
        Text(
            text = description,
            color = textColor,
            style = PpoPpiTheme.typography.body1,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun ChecklistScreenPreview() {
    PpoPpiTheme {
        ChecklistScreen(
            onBackClick = { },
            onComplete = { },
        )
    }
}

@Composable
@Preview(showBackground = true, name = "체크된 체크리스트 아이템")
private fun ChecklistItemPreview_Checked() {
    PpoPpiTheme {
        ChecklistItem(
            description = "증상 설명입니다",
            isChecked = true,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
@Preview(showBackground = true, name = "체크 해제된 체크리스트 아이템")
private fun ChecklistItemPreview_Unchecked() {
    PpoPpiTheme {
        ChecklistItem(
            description = "증상 설명입니다",
            isChecked = false,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
