package com.ppoppi.house.data.model.response.diagnosis

import com.ppoppi.house.data.model.response.diagnosis.DiagnosisTodayResponse.SymptomResponse
import com.ppoppi.house.domain.model.Diagnosis
import com.ppoppi.house.domain.model.Symptom
import com.ppoppi.house.domain.model.Triage
import com.ppoppi.house.domain.model.Triage.Companion.from
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiagnosisTodayResponse(
    @SerialName("hasDiagnosis")
    val hasDiagnosis: Boolean,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("triage")
    val triage: String,
    @SerialName("diseaseName")
    val diseaseName: String,
    @SerialName("affectedArea")
    val affectedArea: String,
    @SerialName("confidence")
    val confidence: Int,
    @SerialName("guidanceAction")
    val guidanceAction: String,
    @SerialName("guidanceMessage")
    val guidanceMessage: String,
    @SerialName("guidanceWarning")
    val guidanceWarning: String,
    @SerialName("symptoms")
    val symptoms: List<SymptomResponse>,
) {
    @Serializable
    data class SymptomResponse(
        @SerialName("symptomId")
        val symptomId: Long,
        @SerialName("description")
        val description: String,
    )
}

fun SymptomResponse.toDomain(): Symptom =
    Symptom(
        id = this.symptomId,
        description = this.description,
    )

fun DiagnosisTodayResponse.toDomain(): Diagnosis =
    Diagnosis(
        hasDiagnosis = this.hasDiagnosis,
        imageUrl = this.imageUrl,
        triage = Triage.from(this.triage),
        diseaseName = this.diseaseName,
        affectedArea = this.affectedArea,
        triageConfidence = this.confidence,
        guideAction = this.guidanceAction,
        guideMessage = this.guidanceMessage,
        guideWarning = this.guidanceWarning,
        symptoms = this.symptoms.map { it.toDomain() },
    )
