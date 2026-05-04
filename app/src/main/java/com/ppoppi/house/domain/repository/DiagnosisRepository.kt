package com.ppoppi.house.domain.repository

import com.ppoppi.house.data.model.response.diagnosis.DiagnosisTodayResponse
import java.time.LocalDate

interface DiagnosisRepository {
    // TODO: API 수정 후 도메인 모델로 변경 필요
    suspend fun getDiagnosisToday(
        petId: Long,
        date: LocalDate,
    ): DiagnosisTodayResponse
}
