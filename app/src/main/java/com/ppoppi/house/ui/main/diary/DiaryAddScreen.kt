package com.ppoppi.house.ui.main.diary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ppoppi.house.ui.component.BottomBarButton
import com.ppoppi.house.ui.main.diary.component.DiaryAddTopBar
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.White

@Composable
fun DiaryAddScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = { DiaryAddTopBar(onBackClick) },
        modifier =
            modifier
                .background(White),
        containerColor = White,
        bottomBar = {
            BottomBarButton(
                onClick = {
                    // TODO: 다이어리 저장 API 연동
                },
                text = "다이어리 저장",
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DiaryAddScreenPreview() {
    PpoPpiTheme {
        DiaryAddScreen(onBackClick = {})
    }
}
