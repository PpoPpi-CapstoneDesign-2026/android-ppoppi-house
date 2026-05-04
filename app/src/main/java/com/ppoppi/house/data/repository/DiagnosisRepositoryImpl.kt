package com.ppoppi.house.data.repository

import com.ppoppi.house.data.model.response.diagnosis.DiagnosisTodayResponse
import com.ppoppi.house.data.service.DiagnosisService
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
        ): DiagnosisTodayResponse {
            val result = diagnosisService.getTodayDiagnosis(petId, date.format())
            // TODO: 도메인으로 변환 로직 필요
            return result
        }
    }
