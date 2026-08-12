package com.ppoppi.house.domain.repository

import com.ppoppi.house.domain.model.Symptom

interface SymptomRepository {
    suspend fun getSymptoms(): List<Symptom>
}
