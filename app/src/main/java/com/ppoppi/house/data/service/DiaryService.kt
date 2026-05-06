package com.ppoppi.house.data.service

import com.ppoppi.house.data.model.response.diary.MonthDiariesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DiaryService {
    @GET("/diaries/month")
    suspend fun getDiariesMonth(
        @Query("year") year: Int,
        @Query("month") month: Int,
    ): MonthDiariesResponse
}
