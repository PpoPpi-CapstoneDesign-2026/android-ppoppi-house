package com.ppoppi.house.domain.repository

import com.ppoppi.house.data.model.response.auth.LoginResponse
import com.ppoppi.house.data.model.response.auth.TokenResponse

interface AuthRepository {
    suspend fun loginWithKakao(kakaoToken: String): LoginResponse

    suspend fun authRefresh(refreshToken: String): TokenResponse
}
