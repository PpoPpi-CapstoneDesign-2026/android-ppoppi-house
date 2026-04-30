package com.ppoppi.house.ui.main.diary

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.ui.main.diary.component.Calendar
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.PpoPpiTheme
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

    // ✅ 스크롤 위치에 따라 달력 접힘 여부 결정
    val isCollapsed by remember {
        derivedStateOf {
            // 첫 번째 아이템(타이틀)이 완전히 사라지거나 조금이라도 스크롤되면 접힘
            listState.firstVisibleItemIndex > 0 || listState.firstVisibleItemScrollOffset > 0
        }
    }

    LazyColumn(
        state = listState,
        modifier =
            modifier
                .fillMaxSize()
                .background(White),
    ) {
        // 상단 고정 달력 (isCollapsed 상태에 따라 높이가 변함)
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
                        .background(Primary50), // 원하는 색상
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
}

@Composable
@Preview(showBackground = true)
private fun DiaryScreenPreview() {
    PpoPpiTheme {
        DiaryScreen()
    }
}
