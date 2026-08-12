package com.ppoppi.house.data.service

import com.ppoppi.house.data.model.request.hospital.HospitalListRequest
import com.ppoppi.house.data.model.response.hospital.HospitalInfoResponse
import com.ppoppi.house.data.model.response.hospital.HospitalListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface HospitalService {
    @POST("/hospitals/search")
    suspend fun postHospitalsSearch(
        @Body request: HospitalListRequest,
    ): HospitalListResponse

    @GET("/hospitals/{hospitalId}")
    suspend fun getHospitalInfo(
        @Path("hospitalId") hospitalId: Long,
        @Query("centerLat") lat: Double,
        @Query("centerLng") lng: Double,
    ): HospitalInfoResponse
}
