package com.ppoppi.house.domain.repository

import com.ppoppi.house.data.model.LoginResponse
import com.ppoppi.house.data.model.TokenResponse

interface LoginRepository {
    suspend fun loginWithKakao(kakaoToken: String): LoginResponse

    suspend fun authRefresh(refreshToken: String): TokenResponse
}
