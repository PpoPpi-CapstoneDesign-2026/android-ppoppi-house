package com.ppoppi.house.domain

data class Diagnosis(
    val guideTitle: String,
    val triageKey: Triage,
    val triageConfidence: Double,
    val affectedArea: String,
    val guideMsg: String,
    val guideAction: String,
    val imageUrl: String?
)
