package com.ppoppi.house.data.repository

import com.ppoppi.house.data.model.response.diagnosis.toDomain
import com.ppoppi.house.data.service.DiagnosisService
import com.ppoppi.house.domain.model.Diagnosis
import com.ppoppi.house.domain.repository.DiagnosisRepository
import com.ppoppi.house.ui.util.format
import okhttp3.MultipartBody
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
        ): Diagnosis = diagnosisService.getTodayDiagnosis(petId, date.format()).toDomain()

        override suspend fun postDiagnosis(
            petId: Long,
            symptomIds: List<Int>,
            image: MultipartBody.Part,
        ): Diagnosis = diagnosisService.postDiagnosis(petId, symptomIds, image).toDomain()
    }
