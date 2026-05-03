package com.ppoppi.house.data.repository

import com.ppoppi.house.data.service.SymptomService
import com.ppoppi.house.domain.model.Symptom
import com.ppoppi.house.domain.repository.SymptomRepository
import javax.inject.Inject

class SymptomRepositoryImpl @Inject constructor(
    private val symptomService: SymptomService,
) : SymptomRepository {
    override suspend fun getSymptoms(): List<Symptom> {
        val symptoms = symptomService.getSymptoms()
        return symptoms.map { Symptom(it.id, it.description) }
    }
}
