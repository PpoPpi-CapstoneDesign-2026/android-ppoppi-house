package com.ppoppi.house.data.service

import com.ppoppi.house.data.model.response.symptom.SymptomResponse
import retrofit2.http.GET

interface SymptomService {
    @GET("/symptoms")
    suspend fun getSymptoms(): SymptomResponse
}
