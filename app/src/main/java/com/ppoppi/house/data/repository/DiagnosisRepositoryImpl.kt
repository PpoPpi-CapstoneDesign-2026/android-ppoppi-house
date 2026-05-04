package com.ppoppi.house.data.repository

import com.ppoppi.house.data.model.response.diagnosis.toDomain
import com.ppoppi.house.data.service.DiagnosisService
import com.ppoppi.house.domain.model.Diagnosis
import com.ppoppi.house.domain.repository.DiagnosisRepository
import com.ppoppi.house.ui.util.format
import java.time.LocalDate
import javax.inject.Inject

class DiagnosisRepositoryImpl
    @Inject
    constructor(
        private val diagnosisService: DiagnosisService,
    ) : DiagnosisRepository {
        override suspend fun getDiagnosisToday(
            petId: Long,
            date: LocalDate,
        ): Diagnosis {
            val result = diagnosisService.getTodayDiagnosis(petId, date.format()).toDomain()
            return result
        }
    }
