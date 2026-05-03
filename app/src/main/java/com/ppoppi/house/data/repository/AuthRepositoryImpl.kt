package com.ppoppi.house.data.repository

import com.ppoppi.house.data.model.request.LoginRequest
import com.ppoppi.house.data.model.request.ReissueRequest
import com.ppoppi.house.data.model.response.auth.LoginResponse
import com.ppoppi.house.data.model.response.auth.TokenResponse
import com.ppoppi.house.data.service.AuthService
import com.ppoppi.house.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl
    @Inject
    constructor(
        private val loginApiService: AuthService,
    ) : AuthRepository {
        override suspend fun loginWithKakao(kakaoToken: String): LoginResponse = loginApiService.postAuthKakao(LoginRequest(kakaoToken))

        override suspend fun authRefresh(refreshToken: String): TokenResponse =
            loginApiService.postAuthReissue(ReissueRequest(refreshToken = refreshToken))
    }
