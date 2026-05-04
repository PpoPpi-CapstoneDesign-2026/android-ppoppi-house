package com.ppoppi.house.data.model.response.diagnosis

import com.ppoppi.house.domain.model.Diagnosis
import com.ppoppi.house.domain.model.Triage
import com.ppoppi.house.domain.model.Triage.Companion.from
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiagnosisResponse(
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
)

fun DiagnosisResponse.toDomain(): Diagnosis =
    Diagnosis(
        hasDiagnosis = true,
        imageUrl = this.imageUrl,
        triage = Triage.from(this.triage),
        diseaseName = this.diseaseName,
        affectedArea = this.affectedArea,
        triageConfidence = this.confidence,
        guideAction = this.guidanceAction,
        guideMessage = this.guidanceMessage,
        guideWarning = this.guidanceWarning,
        symptoms = emptyList(),
    )
