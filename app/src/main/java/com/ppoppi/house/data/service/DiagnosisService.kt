package com.ppoppi.house.data.service

import com.ppoppi.house.data.model.response.diagnosis.DiagnosisResponse
import com.ppoppi.house.data.model.response.diagnosis.DiagnosisTodayResponse
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface DiagnosisService {
    @GET("/diagnoses/today")
    suspend fun getTodayDiagnosis(
        @Query("petId") petId: Long,
        @Query("date") date: String,
    ): DiagnosisTodayResponse

    @Multipart
    @POST("/diagnoses")
    suspend fun postDiagnosis(
        @Query("petId") petId: Long,
        @Query("symptomIds") symptomIds: List<Int>,
        @Part image: MultipartBody.Part,
    ): DiagnosisResponse
}
