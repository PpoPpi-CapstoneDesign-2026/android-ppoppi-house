package com.ppoppi.house.data.repository

import com.ppoppi.house.data.model.request.diary.UpdateDairyRequest
import com.ppoppi.house.data.model.response.diary.toDomain
import com.ppoppi.house.data.service.DiaryService
import com.ppoppi.house.domain.model.Diary
import com.ppoppi.house.domain.model.HealthChecklist
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

    override suspend fun getHealthChecklist(): List<HealthChecklist> {
        val result = diaryService.getHealthChecklist()
        return result.map { it.toDomain() }
    }

    override suspend fun updateDairy(
        diaryId: Long,
        memo: String,
        checkIds: List<Int>,
    ): Result<Unit> {
        val response = diaryService.updateDiary(diaryId, UpdateDairyRequest(diaryId, memo, checkIds))
        return if (response.isSuccessful) Result.success(Unit) else Result.failure(Exception("updateDiary failed: ${response.code()}"))
    }
}
