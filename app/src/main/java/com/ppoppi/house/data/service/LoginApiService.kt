package com.ppoppi.house.data.service

import com.ppoppi.house.data.model.LoginRequest
import com.ppoppi.house.data.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApiService {
    @POST("/api/auth/kakao")
    suspend fun loginWithKakao(@Body request: LoginRequest): LoginResponse
}
