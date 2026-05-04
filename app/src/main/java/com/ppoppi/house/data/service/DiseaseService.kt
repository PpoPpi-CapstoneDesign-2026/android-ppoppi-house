package com.ppoppi.house.data.service

import com.ppoppi.house.data.model.response.disease.DiseaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface DiseaseService {
    @GET("/genetic-diseases/search")
    suspend fun getGeneticDiseaseSearch(
        @Query("keyword") keyword: String,
    ): DiseaseResponse?

    @GET("/genetic-diseases/random")
    suspend fun getGeneticDiseaseRandom(): DiseaseResponse
}
