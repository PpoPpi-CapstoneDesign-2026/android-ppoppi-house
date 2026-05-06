package com.ppoppi.house.ui.main.diary

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ppoppi.house.domain.model.SPECIES
import com.ppoppi.house.ui.main.diary.component.Calendar
import com.ppoppi.house.ui.main.diary.component.DiaryItem
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.Gray100
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.Primary400
import com.ppoppi.house.ui.theme.Primary50
import com.ppoppi.house.ui.theme.Primary600
import com.ppoppi.house.ui.theme.White
import com.ppoppi.house.ui.util.getColoredText
import com.ppoppi.house.ui.util.noRippleClickable
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.TextStyle
import java.util.Locale

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DiaryScreen(
    modifier: Modifier = Modifier,
    currentTime: LocalDate = LocalDate.now(),
    viewModel: DiaryViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val listState = rememberLazyListState()
    val density = LocalDensity.current

    var yearMonth by remember { mutableStateOf(YearMonth.now()) }
    var selectedDate by remember { mutableStateOf(currentTime) }
    val monthDiaries by viewModel.monthDiaries.collectAsState()
    val pets by viewModel.pets.collectAsState()
    val dailyDiaries by viewModel.dailyDiaries.collectAsState()
    var isFabExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(yearMonth) {
        viewModel.loadMonthDiaries(yearMonth)
    }

    LaunchedEffect(selectedDate) {
        viewModel.loadDailyDiaries(selectedDate)
    }

    var headerScrollOffset by remember { mutableStateOf(0f) }
    val collapseThreshold = with(density) { 150.dp.toPx() }

    val nestedScrollConnection =
        remember {
            object : NestedScrollConnection {
                override fun onPreScroll(
                    available: Offset,
                    source: NestedScrollSource,
                ): Offset {
                    val delta = available.y

                    return if (delta < 0) {
                        if (headerScrollOffset > -collapseThreshold) {
                            val consumed =
                                delta.coerceAtLeast(-collapseThreshold - headerScrollOffset)
                            headerScrollOffset += consumed
                            Offset(0f, consumed)
                        } else {
                            Offset.Zero
                        }
                    } else {
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
                    monthDiary = monthDiaries,
                )
            }

            // 날짜 표시 헤더
            item {
                Text(
                    text =
                        "${selectedDate.monthValue}월 ${selectedDate.dayOfMonth}일 (${
                            selectedDate.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN)
                        })".getColoredText(
                            Primary600,
                            "(${
                                selectedDate.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN)
                            })",
                        ),
                    style = PpoPpiTheme.typography.title1,
                    color = Black,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 12.dp),
                )
            }

            // 개별 다이어리 아이템들
            items(dailyDiaries) { diary ->
                val pet = pets.find { it.id == diary.petId }
                if (pet != null) {
                    Box(modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)) {
                        DiaryItem(
                            pet = pet,
                            diagnosis = diary.diagnosis,
                            memo = diary.memo,
                            healthChecklist = diary.healthChecklist,
                        )
                    }
                }
            }

            if (dailyDiaries.isEmpty()) {
                item {
                    Text(
                        text = "아직 다이어리가 없어요",
                        style = PpoPpiTheme.typography.body2,
                        color = Black,
                        modifier =
                            Modifier.padding(
                                start = 20.dp,
                                end = 20.dp,
                                top = 20.dp,
                            ),
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        }

        Column(
            modifier =
                Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            AnimatedVisibility(
                visible = isFabExpanded,
                enter = fadeIn() + expandVertically(expandFrom = Alignment.Bottom),
                exit = fadeOut() + shrinkVertically(shrinkTowards = Alignment.Bottom),
            ) {
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    pets.forEach { pet ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Text(
                                text = pet.name,
                                style = PpoPpiTheme.typography.title3,
                                color = Black,
                                modifier =
                                    Modifier
                                        .wrapContentSize()
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(White)
                                        .border(1.dp, Gray100, RoundedCornerShape(8.dp))
                                        .padding(horizontal = 8.dp, vertical = 8.dp),
                            )

                            Box(
                                modifier =
                                    Modifier
                                        .size(60.dp)
                                        .clip(RoundedCornerShape(100.dp))
                                        .background(White, RoundedCornerShape(100.dp))
                                        .border(1.dp, Gray100, RoundedCornerShape(100.dp))
                                        .noRippleClickable(
                                            onClick = {
                                                isFabExpanded = false
                                                pet.id?.let { id ->
                                                    context.startActivity(
                                                        DiaryAddActivity.newIntent(
                                                            context,
                                                            id,
                                                        ),
                                                    )
                                                }
                                            },
                                        ).padding(horizontal = 16.dp, vertical = 10.dp),
                                contentAlignment = Alignment.Center,
                            ) {
                                Text(
                                    text = if (pet.species == SPECIES.DOG) "🐶" else "🐱",
                                    style = PpoPpiTheme.typography.headline1,
                                )
                            }
                        }
                    }
                }
            }

            Box(
                modifier =
                    Modifier
                        .size(70.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .background(Primary400, RoundedCornerShape(100.dp))
                        .noRippleClickable(onClick = { isFabExpanded = !isFabExpanded }),
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
}

@Composable
@Preview(showBackground = true)
private fun DiaryScreenPreview() {
    PpoPpiTheme {
        DiaryScreen()
    }
}
