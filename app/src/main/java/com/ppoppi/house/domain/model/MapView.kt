package com.ppoppi.house.domain.model

data class MapView(
    val bounds: Bounds,
    val zoom: Int,
    val center: Center,
    val emergencyOnly: Boolean = false,
    val limit: Int
) {

    data class Bounds(
        val northeast: LatLng,
        val southwest: LatLng
    )

    data class Center(
        val lat: Double,
        val lng: Double
    )

    data class LatLng(
        val lat: Double,
        val lng: Double
    )
}
