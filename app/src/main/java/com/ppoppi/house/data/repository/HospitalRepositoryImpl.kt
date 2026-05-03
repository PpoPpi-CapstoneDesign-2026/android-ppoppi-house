package com.ppoppi.house.data.repository

import com.ppoppi.house.data.model.request.hospital.toData
import com.ppoppi.house.data.model.response.toDomain
import com.ppoppi.house.data.service.HospitalService
import com.ppoppi.house.domain.model.HospitalItem
import com.ppoppi.house.domain.model.MapView
import com.ppoppi.house.domain.repository.HospitalRepository
import javax.inject.Inject

class HospitalRepositoryImpl
    @Inject
    constructor(
        private val hospitalService: HospitalService,
    ) : HospitalRepository {
        override suspend fun postHospitalsSearch(mapView: MapView): List<HospitalItem> {
            val hospitals = hospitalService.postHospitalsSearch(mapView.toData())
            return hospitals.map { it.toDomain() }
        }
    }
