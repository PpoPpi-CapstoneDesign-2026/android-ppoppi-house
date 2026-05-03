package com.ppoppi.house.data.service

import com.ppoppi.house.data.model.request.hospital.HospitalListRequest
import com.ppoppi.house.data.model.response.HospitalListResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface HospitalService {
    @POST("/hospitals/search")
    suspend fun postHospitalsSearch(
        @Body request: HospitalListRequest
    ): HospitalListResponse
}
