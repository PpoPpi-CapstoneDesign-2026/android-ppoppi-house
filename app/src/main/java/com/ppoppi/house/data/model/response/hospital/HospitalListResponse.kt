package com.ppoppi.house.data.model.response.hospital

import com.ppoppi.house.domain.model.HospitalItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class HospitalListResponse : ArrayList<HospitalListResponse.HospitalListResponseItem>() {
    @Serializable
    data class HospitalListResponseItem(
        @SerialName("hospitalId")
        val hospitalId: Int,
        @SerialName("name")
        val name: String,
        @SerialName("latitude")
        val latitude: Double,
        @SerialName("longitude")
        val longitude: Double,
        @SerialName("distanceMeter")
        val distanceMeter: Int,
        @SerialName("is24hr")
        val is24hr: Boolean,
    )
}

fun HospitalListResponse.HospitalListResponseItem.toDomain(): HospitalItem =
    HospitalItem(
        hospitalId = this.hospitalId,
        name = this.name,
        latitude = this.latitude,
        longitude = this.longitude,
        distanceMeter = this.distanceMeter,
        is24hr = this.is24hr,
    )
