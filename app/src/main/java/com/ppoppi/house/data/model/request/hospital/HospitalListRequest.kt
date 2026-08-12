package com.ppoppi.house.data.model.request.hospital

import com.ppoppi.house.domain.model.MapView
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HospitalListRequest(
    @SerialName("bounds")
    val bounds: Bounds,
    @SerialName("zoom")
    val zoom: Int,
    @SerialName("center")
    val center: Center,
    @SerialName("emergencyOnly")
    val emergencyOnly: Boolean = false,
    @SerialName("limit")
    val limit: Int,
) {
    @Serializable
    data class Bounds(
        @SerialName("northeast")
        val northeast: Northeast,
        @SerialName("southwest")
        val southwest: Southwest,
    ) {
        @Serializable
        data class Northeast(
            @SerialName("lat")
            val lat: Double,
            @SerialName("lng")
            val lng: Double,
        )

        @Serializable
        data class Southwest(
            @SerialName("lat")
            val lat: Double,
            @SerialName("lng")
            val lng: Double,
        )
    }

    @Serializable
    data class Center(
        @SerialName("lat")
        val lat: Double,
        @SerialName("lng")
        val lng: Double,
    )
}

fun MapView.toData(): HospitalListRequest =
    HospitalListRequest(
        bounds =
            HospitalListRequest.Bounds(
                northeast =
                    HospitalListRequest.Bounds.Northeast(
                        lat = bounds.northeast.lat,
                        lng = bounds.northeast.lng,
                    ),
                southwest =
                    HospitalListRequest.Bounds.Southwest(
                        lat = bounds.southwest.lat,
                        lng = bounds.southwest.lng,
                    ),
            ),
        zoom = zoom,
        center =
            HospitalListRequest.Center(
                lat = center.lat,
                lng = center.lng,
            ),
        emergencyOnly = emergencyOnly,
        limit = limit,
    )
