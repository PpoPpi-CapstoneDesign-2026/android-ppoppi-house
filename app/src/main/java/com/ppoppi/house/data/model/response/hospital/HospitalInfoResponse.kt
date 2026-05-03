package com.ppoppi.house.data.model.response.hospital

import com.ppoppi.house.domain.model.HospitalInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HospitalInfoResponse(
    @SerialName("hospitalId")
    val hospitalId: Long,
    @SerialName("name")
    val name: String,
    @SerialName("address")
    val address: String,
    @SerialName("callNumber")
    val callNumber: String,
    @SerialName("businessHours")
    val businessHours: String,
    @SerialName("operationLabel")
    val operationLabel: String,
    @SerialName("distanceMeter")
    val distanceMeter: Long,
    @SerialName("is24hr")
    val is24hr: Boolean,
)

fun HospitalInfoResponse.toDomain(): HospitalInfo =
    HospitalInfo(
        id = hospitalId,
        name = name,
        address = address,
        callNumber = callNumber,
        businessHours = businessHours,
        operationLabel = operationLabel,
        distanceMeter = distanceMeter,
        is24Hour = is24hr,
    )
