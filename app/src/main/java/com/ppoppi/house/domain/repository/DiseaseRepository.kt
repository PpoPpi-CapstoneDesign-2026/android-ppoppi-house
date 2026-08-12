package com.ppoppi.house.domain.repository

import com.ppoppi.house.domain.model.Disease

interface DiseaseRepository {
    suspend fun getGeneticDiseaseSearch(keyword: String): List<Disease>

    suspend fun getGeneticDiseaseRandom(): List<Disease>
}
