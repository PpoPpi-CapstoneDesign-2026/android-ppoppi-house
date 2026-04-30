package com.ppoppi.house.ui.main.diary.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.ui.theme.Black
import com.ppoppi.house.ui.theme.Blue400
import com.ppoppi.house.ui.theme.Gray100
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.Primary100
import com.ppoppi.house.ui.theme.Primary50
import com.ppoppi.house.ui.theme.Red400
import com.ppoppi.house.ui.util.noRippleClickable
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun Calendar(
    onClickMinus: () -> Unit,
    onClickPlus: () -> Unit,
    onSelectDate: (LocalDate) -> Unit,
    selectedDate: LocalDate,
    modifier: Modifier = Modifier,
    yearMonth: YearMonth = YearMonth.now(),
    currentTime: YearMonth = YearMonth.now(),
    isCollapsed: Boolean = false,
) {
    val firstDayOfWeek = yearMonth.atDay(1).dayOfWeek.value % 7
    val totalDays = yearMonth.lengthOfMonth()
    val cells: List<Int?> = List(firstDayOfWeek) { null } + (1..totalDays).toList()
    val weeks = cells.chunked(7)

    val selectedWeekIndex =
        if (selectedDate.year == yearMonth.year && selectedDate.month == yearMonth.month) {
            weeks.indexOfFirst { week -> week.contains(selectedDate.dayOfMonth) }.coerceAtLeast(0)
        } else {
            0
        }

    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .animateContentSize(animationSpec = spring(stiffness = 800f)),
        // ✅ 높이 변화 애니메이션 추가
    ) {
        // 월 선택 Row
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Box(
                modifier =
                    Modifier
                        .size(48.dp)
                        .noRippleClickable(onClickMinus),
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "이전 달",
                    modifier = Modifier.align(Alignment.Center),
                )
            }
            Text(
                text =
                    if (currentTime.year == yearMonth.year) {
                        "${yearMonth.monthValue} 월"
                    } else {
                        "${yearMonth.year}년 ${yearMonth.monthValue}월"
                    },
                style = PpoPpiTheme.typography.headline1,
            )
            Box(
                modifier =
                    Modifier
                        .size(48.dp)
                        .noRippleClickable(onClickPlus),
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                    contentDescription = "다음 달",
                    modifier = Modifier.align(Alignment.Center),
                )
            }
        }

        // 요일 행
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            listOf(
                "일" to Red400,
                "월" to Black,
                "화" to Black,
                "수" to Black,
                "목" to Black,
                "금" to Black,
                "토" to Blue400,
            ).forEach { (label, color) ->
                Box(modifier = Modifier.size(48.dp), contentAlignment = Alignment.Center) {
                    Text(text = label, style = PpoPpiTheme.typography.body3, color = color)
                }
            }
        }

        // ✅ 모든 주를 렌더링하되, 선택되지 않은 주는 AnimatedVisibility로 제어
        weeks.forEachIndexed { index, week ->
            val isVisible = !isCollapsed || index == selectedWeekIndex

            AnimatedVisibility(
                visible = isVisible,
                enter = expandVertically(),
                exit = shrinkVertically(),
            ) {
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                            .padding(horizontal = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    val paddedWeek = week + List(7 - week.size) { null }

                    paddedWeek.forEachIndexed { dayIndex, day ->
                        val isSelected = day != null && selectedDate == yearMonth.atDay(day)
                        val isSunday = dayIndex == 0
                        val isSaturday = dayIndex == 6

                        Box(
                            modifier =
                                Modifier
                                    .size(48.dp)
                                    .noRippleClickable({
                                        if (day != null) onSelectDate(yearMonth.atDay(day))
                                    })
                                    .then(
                                        if (isSelected) {
                                            Modifier
                                                .padding(horizontal = 4.dp)
                                                .background(
                                                    color = Primary50,
                                                    shape = RoundedCornerShape(4.dp),
                                                ).border(
                                                    color = Primary100,
                                                    width = 1.dp,
                                                    shape = RoundedCornerShape(4.dp),
                                                )
                                        } else {
                                            Modifier
                                        },
                                    ),
                            contentAlignment = Alignment.Center,
                        ) {
                            if (day != null) {
                                Text(
                                    text = day.toString(),
                                    style = PpoPpiTheme.typography.body3,
                                    color =
                                        when {
                                            isSunday -> Red400
                                            isSaturday -> Blue400
                                            else -> Black
                                        },
                                )
                            }
                        }
                    }
                }
            }
        }

        HorizontalDivider(modifier = Modifier.padding(horizontal = 24.dp), color = Gray100)
    }
}

@Composable
@Preview(showBackground = true)
private fun CalendarPreview() {
    PpoPpiTheme {
        Calendar(
            onClickMinus = {},
            onClickPlus = {},
            selectedDate = LocalDate.now(),
            onSelectDate = { },
        )
    }
}
