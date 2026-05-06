package com.ppoppi.house.data.repository

import androidx.camera.core.impl.utils.LiveDataUtil.map
import com.ppoppi.house.data.model.response.diary.toDomain
import com.ppoppi.house.data.service.DiaryService
import com.ppoppi.house.domain.model.Diary
import com.ppoppi.house.domain.model.MonthDiary
import com.ppoppi.house.domain.repository.DiaryRepository
import javax.inject.Inject

class DiaryRepositoryImpl
    @Inject
    constructor(
        private val diaryService: DiaryService,
    ) : DiaryRepository {
        override suspend fun getDiariesMonth(
            year: Int,
            month: Int,
        ): List<MonthDiary> {
            val result = diaryService.getDiariesMonth(year, month)
            return result.diaryList.map { it.toDomain() }
        }

        override suspend fun getDiariesDaily(
            year: Int,
            month: Int,
            day: Int,
        ): List<Diary> {
            val result = diaryService.getDiariesDaily(year, month, day)
            return result.map { it.toDomain() }
        }
    }
