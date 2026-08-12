package com.ppoppi.house.domain.model

data class HospitalItem(
    val hospitalId: Int,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val distanceMeter: Int,
    val is24hr: Boolean,
)
