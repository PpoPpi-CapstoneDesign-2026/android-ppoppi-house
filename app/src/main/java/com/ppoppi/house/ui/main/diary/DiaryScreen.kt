package com.ppoppi.house.ui.main.diary

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.ui.main.diary.component.Calendar
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.Primary400
import com.ppoppi.house.ui.theme.Primary50
import com.ppoppi.house.ui.theme.White
import java.time.LocalDate
import java.time.YearMonth

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DiaryScreen(
    modifier: Modifier = Modifier,
    currentTime: LocalDate = LocalDate.now(),
) {
    val listState = rememberLazyListState()

    var yearMonth by remember { mutableStateOf(YearMonth.now()) }
    var selectedDate by remember { mutableStateOf(currentTime) }

    val isCollapsed by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0 || listState.firstVisibleItemScrollOffset > 0
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            state = listState,
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(White),
        ) {
            stickyHeader {
                Calendar(
                    onClickMinus = {
                        yearMonth = yearMonth.minusMonths(1)
                        selectedDate = selectedDate.minusMonths(1).withDayOfMonth(1)
                    },
                    onClickPlus = {
                        yearMonth = yearMonth.plusMonths(1)
                        selectedDate = selectedDate.plusMonths(1).withDayOfMonth(1)
                    },
                    onSelectDate = { selectedDate = it },
                    yearMonth = yearMonth,
                    selectedDate = selectedDate,
                    isCollapsed = isCollapsed,
                    modifier = Modifier.background(White),
                )
            }

            // 다이어리 리스트 예시 데이터
            item {
                Column(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .background(Primary50),
                ) {
                    repeat(20) {
                        Text(
                            text = "다이어리 기록 내용 $it",
                            modifier = Modifier.padding(top = 24.dp, start = 26.dp),
                            style = PpoPpiTheme.typography.headline1,
                            color = Black,
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .size(50.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(Primary400, RoundedCornerShape(100.dp))
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                tint = White,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun DiaryScreenPreview() {
    PpoPpiTheme {
        DiaryScreen()
    }
}
