package com.ppoppi.house.domain.model

data class Diagnosis(
    val hasDiagnosis: Boolean = false,
    val imageUrl: String?,
    val triage: Triage,
    val diseaseName: String,
    val affectedArea: String,
    val triageConfidence: Int,
    val guideAction: String,
    val guideMessage: String,
    val guideWarning: String,
    val symptoms: List<Symptom>? = null,
)
