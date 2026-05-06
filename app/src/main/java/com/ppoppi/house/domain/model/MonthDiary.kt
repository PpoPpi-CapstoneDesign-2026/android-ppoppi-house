package com.ppoppi.house.domain.model

import java.time.LocalDate

data class MonthDiary(
    val date: LocalDate,
    val color: List<COLOR>,
)
