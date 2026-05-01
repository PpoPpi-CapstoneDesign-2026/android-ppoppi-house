package com.ppoppi.house.domain

import androidx.compose.ui.graphics.Color
import com.google.android.gms.maps.model.LatLng
import com.ppoppi.house.ui.theme.Gray300
import com.ppoppi.house.ui.theme.Primary200

data class VetHospital(
    val name: String,
    val businessStatus: BusinessStatus,
    val businessTime: String,
    val location: LatLng,
    val phone: String,
    val distance: Int,
    val address: String,
)

enum class BusinessStatus(
    val label: String,
) {
    OPEN("영업 중"),
    CLOSED("영업 종료"),
    ;

    companion object {
        fun BusinessStatus.toColor(): Color =
            when (this) {
                OPEN -> Primary200
                CLOSED -> Gray300
            }
    }
}
