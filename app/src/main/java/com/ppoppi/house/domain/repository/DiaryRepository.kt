package com.ppoppi.house.domain.repository

import com.ppoppi.house.domain.model.MonthDiary

interface DiaryRepository {
    suspend fun getDiariesMonth(
        year: Int,
        month: Int,
    ): List<MonthDiary>
}
