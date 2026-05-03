package com.ppoppi.house.domain.repository

import com.ppoppi.house.domain.model.HospitalItem
import com.ppoppi.house.domain.model.MapView

interface HospitalRepository {
    suspend fun postHospitalsSearch(
        mapView: MapView,
    ): List<HospitalItem>
}
