package com.ppoppi.house.domain.repository

import com.ppoppi.house.data.model.LoginResponse

interface LoginRepository {
    suspend fun loginWithKakao(kakaoToken: String): LoginResponse
}
