package com.ppoppi.house.data.service

import com.ppoppi.house.data.model.response.diagnosis.DiagnosisTodayResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DiagnosisService {
    @GET("/diagnosis/today")
    suspend fun getTodayDiagnosis(
        @Query("petId") petId: Long,
        @Query("date") date: String,
    ): DiagnosisTodayResponse
}
