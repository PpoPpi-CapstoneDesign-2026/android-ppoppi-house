package com.ppoppi.house.data.service

import com.ppoppi.house.data.model.request.diary.DiaryRequest
import com.ppoppi.house.data.model.request.diary.UpdateDairyRequest
import com.ppoppi.house.data.model.response.diary.DailyDiariesResponse
import com.ppoppi.house.data.model.response.diary.HealthChecklistResponse
import com.ppoppi.house.data.model.response.diary.MonthDiariesResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface DiaryService {
    @GET("/diaries/month")
    suspend fun getDiariesMonth(
        @Query("year") year: Int,
        @Query("month") month: Int,
    ): MonthDiariesResponse

    @GET("/diaries")
    suspend fun getDiariesDaily(
        @Query("year") year: Int,
        @Query("month") month: Int,
        @Query("day") day: Int,
    ): DailyDiariesResponse

    @GET("/diaries/checks")
    suspend fun getHealthChecklist(): HealthChecklistResponse

    @PUT("/diaries/{diaryId}")
    suspend fun updateDiary(
        @Path("diaryId") diaryId: Long,
        @Body updateDiaryRequest: UpdateDairyRequest,
    ): Response<Unit>

    @POST("/diaries")
    suspend fun postDiary(
        @Body diaryRequest: DiaryRequest,
    ): Response<Unit>

    @DELETE("/diaries/{diaryId}")
    suspend fun deleteDiary(
        @Path("diaryId") diaryId: Long,
    ): Response<Unit>
}
