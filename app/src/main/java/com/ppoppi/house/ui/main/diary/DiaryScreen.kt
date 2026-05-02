package com.ppoppi.house.ui.main.diary

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.domain.model.COLOR
import com.ppoppi.house.domain.model.Diagnosis
import com.ppoppi.house.domain.model.Pet
import com.ppoppi.house.domain.model.SEX
import com.ppoppi.house.domain.model.SPECIES
import com.ppoppi.house.domain.model.Triage
import com.ppoppi.house.ui.main.diary.component.Calendar
import com.ppoppi.house.ui.main.diary.component.DiaryItem
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.Primary400
import com.ppoppi.house.ui.theme.Primary50
import com.ppoppi.house.ui.theme.Primary800
import com.ppoppi.house.ui.theme.White
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DiaryScreen(
    modifier: Modifier = Modifier,
    currentTime: LocalDate = LocalDate.now(),
) {
    val listState = rememberLazyListState()
    val density = LocalDensity.current

    var yearMonth by remember { mutableStateOf(YearMonth.now()) }
    var selectedDate by remember { mutableStateOf(currentTime) }

    // 달력 접힘 제어를 위한 오프셋 상태
    var headerScrollOffset by remember { mutableStateOf(0f) }
    val collapseThreshold = with(density) { 150.dp.toPx() } // 접히기 전까지 소비할 스크롤 양

    val nestedScrollConnection =
        remember {
            object : NestedScrollConnection {
                override fun onPreScroll(
                    available: Offset,
                    source: NestedScrollSource,
                ): Offset {
                    val delta = available.y

                    return if (delta < 0) { // 위로 스크롤 (올리기)
                        if (headerScrollOffset > -collapseThreshold) {
                            val consumed = delta.coerceAtLeast(-collapseThreshold - headerScrollOffset)
                            headerScrollOffset += consumed
                            Offset(0f, consumed)
                        } else {
                            Offset.Zero
                        }
                    } else { // 아래로 스크롤 (내리기)
                        if (listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset == 0) {
                            if (headerScrollOffset < 0) {
                                val consumed = delta.coerceAtMost(-headerScrollOffset)
                                headerScrollOffset += consumed
                                Offset(0f, consumed)
                            } else {
                                Offset.Zero
                            }
                        } else {
                            Offset.Zero
                        }
                    }
                }
            }
        }

    val isCollapsed by remember {
        derivedStateOf { headerScrollOffset < -20f }
    }

    // 예시 데이터 리스트
    val diaryItems =
        remember {
            listOf(
                DiaryData(
                    pet = Pet("뽀삐", SPECIES.CAT, "몰라", 1, SEX.MALE, COLOR.PRIMARY600),
                    diagnosis =
                        Diagnosis(
                            "결막염",
                            Triage.SOON,
                            80.0,
                            "각막",
                            "ㅁㄴㅇㄹ",
                            "ㅁㄴㅇㄹ",
                            "https://picsum.photos/400/400",
                        ),
                    checklist = listOf("눈물", "눈곱"),
                    memo = "",
                    healthChecklist = emptyList(),
                ),
                DiaryData(
                    pet = Pet("나비", SPECIES.CAT, "몰라", 2, SEX.FEMALE, COLOR.PRIMARY600),
                    diagnosis =
                        Diagnosis(
                            "피부염",
                            Triage.MONITOR,
                            75.0,
                            "등",
                            "ㅁㄴㅇㄹ",
                            "ㅁㄴㅇㄹ",
                            "https://picsum.photos/400/400",
                        ),
                    checklist = listOf("가려움"),
                    memo = "오늘은 나비가 어쩌구 저쩌구 메모메모",
                    healthChecklist = listOf("활동량 적음", "식욕 없음"),
                ),
            )
        }

    Box(
        modifier =
            modifier
                .fillMaxSize()
                .background(Primary50)
                .nestedScroll(nestedScrollConnection),
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
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

            // 날짜 표시 헤더
            item {
                Text(
                    text = "${selectedDate.monthValue}월 ${selectedDate.dayOfMonth}일 (${
                        selectedDate.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN)
                    })",
                    style = PpoPpiTheme.typography.title1,
                    color = Primary800,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 12.dp),
                )
            }

            // 개별 다이어리 아이템들
            items(diaryItems) { data ->
                Box(modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)) {
                    DiaryItem(
                        pet = data.pet,
                        diagnosis = data.diagnosis,
                        checklist = data.checklist,
                        memo = data.memo,
                        healthChecklist = data.healthChecklist,
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }

        // 플로팅 버튼
        Box(
            modifier =
                Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
                    .size(50.dp)
                    .clip(RoundedCornerShape(100.dp))
                    .background(Primary400, RoundedCornerShape(100.dp)),
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                tint = White,
                contentDescription = null,
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                        .align(Alignment.Center),
            )
        }
    }
}

data class DiaryData(
    val pet: Pet,
    val diagnosis: Diagnosis,
    val checklist: List<String>,
    val memo: String,
    val healthChecklist: List<String>,
)

@Composable
@Preview(showBackground = true)
private fun DiaryScreenPreview() {
    PpoPpiTheme {
        DiaryScreen()
    }
}
