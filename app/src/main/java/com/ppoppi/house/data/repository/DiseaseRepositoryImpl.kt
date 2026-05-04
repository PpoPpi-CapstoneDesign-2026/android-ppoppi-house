package com.ppoppi.house.data.repository

import com.ppoppi.house.data.model.response.disease.toDomain
import com.ppoppi.house.data.service.DiseaseService
import com.ppoppi.house.domain.model.Disease
import com.ppoppi.house.domain.repository.DiseaseRepository
import jakarta.inject.Inject

class DiseaseRepositoryImpl
    @Inject
    constructor(
        private val diseaseService: DiseaseService,
    ) : DiseaseRepository {
        override suspend fun getGeneticDiseaseSearch(keyword: String): List<Disease> =
            runCatching { diseaseService.getGeneticDiseaseSearch(keyword)?.map { it.toDomain() } }
                .getOrDefault(emptyList()) ?: emptyList()

        override suspend fun getGeneticDiseaseRandom(): List<Disease> = diseaseService.getGeneticDiseaseRandom().map { it.toDomain() }
    }
