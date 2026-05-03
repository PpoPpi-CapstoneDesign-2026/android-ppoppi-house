package com.ppoppi.house.data.service

import com.ppoppi.house.data.model.LoginRequest
import com.ppoppi.house.data.model.LoginResponse
import com.ppoppi.house.data.model.TokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/api/auth/kakao")
    suspend fun postAuthKakao(@Body request: LoginRequest): LoginResponse

    @POST("/api/auth/reissue")
    suspend fun postAuthReissue(@Body refreshToken: String): TokenResponse
}
