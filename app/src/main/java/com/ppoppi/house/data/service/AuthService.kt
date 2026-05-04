package com.ppoppi.house.data.service

import com.ppoppi.house.data.model.request.auth.LoginRequest
import com.ppoppi.house.data.model.request.auth.ReissueRequest
import com.ppoppi.house.data.model.response.auth.LoginResponse
import com.ppoppi.house.data.model.response.auth.TokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/api/auth/kakao")
    suspend fun postAuthKakao(
        @Body request: LoginRequest,
    ): LoginResponse

    @POST("/api/auth/reissue")
    suspend fun postAuthReissue(
        @Body request: ReissueRequest,
    ): TokenResponse
}
