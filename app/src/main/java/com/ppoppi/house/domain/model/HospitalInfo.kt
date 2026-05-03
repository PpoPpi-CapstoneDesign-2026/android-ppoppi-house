package com.ppoppi.house.domain.model

data class HospitalInfo(
    val id: Long,
    val name: String,
    val address: String,
    val callNumber: String,
    val businessHours: String,
    val operationLabel: String,
    val distanceMeter: Long,
    val is24Hour: Boolean,
)
