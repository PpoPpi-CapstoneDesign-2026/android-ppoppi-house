package com.ppoppi.house.domain.model

data class Diary(
    val id: Long,
    val petId: Long,
    val petName: String,
    val diagnosis: Diagnosis?,
    val memo: String,
    val healthChecklist: List<String>,
)
