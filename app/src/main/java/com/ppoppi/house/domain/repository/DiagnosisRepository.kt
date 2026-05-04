package com.ppoppi.house.domain.repository

import com.ppoppi.house.domain.model.Diagnosis
import java.time.LocalDate

interface DiagnosisRepository {
    // TODO: API 수정 후 도메인 모델로 변경 필요
    suspend fun getDiagnosisToday(
        petId: Long,
        date: LocalDate,
    ): Diagnosis
}
