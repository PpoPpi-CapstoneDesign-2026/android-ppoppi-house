package com.ppoppi.house.data.model.response.diagnosis

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiagnosisTodayResponse(
    @SerialName("hasDiagnosis")
    val hasDiagnosis: Boolean,
    @SerialName("diagnosisId")
    val diagnosisId: Long,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("diseaseId")
    val diseaseId: Long,
    @SerialName("diseaseName")
    val diseaseName: String,
    @SerialName("triageKey")
    val triageKey: String,
    @SerialName("triageConfidence")
    val triageConfidence: Double,
    @SerialName("guideMsg")
    val guideMsg: String,
    @SerialName("guideWarn")
    val guideWarn: String,
    @SerialName("guideAction")
    val guideAction: String,
)
