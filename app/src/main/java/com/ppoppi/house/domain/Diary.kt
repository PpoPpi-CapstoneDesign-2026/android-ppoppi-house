package com.ppoppi.house.domain

data class Diary(
    val id: Long,
    val petId: Long,
    val diagnosis: Diagnosis?,
    val checklist: List<String> = emptyList(),
    val memo: String,
    val healthChecklist: List<String>,
)
