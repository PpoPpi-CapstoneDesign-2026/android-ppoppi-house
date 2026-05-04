package com.ppoppi.house.ui.diagnosis.result.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ppoppi.house.ui.component.BottomBarButton
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.Gray200
import com.ppoppi.house.ui.theme.PpoPpiTheme

@Composable
fun FailedContent(
    navigateToDiagnosis: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "🥺",
            fontSize = 64.sp,
        )
        Text(
            text = "AI 분석에 실패했어요",
            style = PpoPpiTheme.typography.title2,
            color = Black,
            modifier = Modifier.padding(top = 30.dp),
        )

        Text(
            text = "다시 진단해 주세요",
            style = PpoPpiTheme.typography.body3,
            color = Gray200,
            modifier = Modifier.padding(top = 10.dp),
        )

        Spacer(modifier = Modifier.height(80.dp))
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        BottomBarButton(
            onClick = navigateToDiagnosis,
            text = "다시 진단하기",
        )
    }
}
