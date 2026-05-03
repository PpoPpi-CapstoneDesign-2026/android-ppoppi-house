package com.ppoppi.house.domain.repository

import com.ppoppi.house.domain.model.HospitalInfo
import com.ppoppi.house.domain.model.HospitalItem
import com.ppoppi.house.domain.model.MapView

interface HospitalRepository {
    suspend fun postHospitalsSearch(mapView: MapView): List<HospitalItem>

    suspend fun getHospitalsInfo(
        hospitalId: Long,
        centerLat: Double,
        centerLng: Double
    ): HospitalInfo
}
