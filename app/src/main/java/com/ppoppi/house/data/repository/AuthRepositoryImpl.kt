package com.ppoppi.house.data.repository

import com.ppoppi.house.data.model.request.auth.LoginRequest
import com.ppoppi.house.data.model.request.auth.ReissueRequest
import com.ppoppi.house.data.model.response.auth.LoginResponse
import com.ppoppi.house.data.model.response.auth.TokenResponse
import com.ppoppi.house.data.datasource.TokenDataSource
import com.ppoppi.house.data.service.AuthService
import com.ppoppi.house.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl
    @Inject
    constructor(
        private val authService: AuthService,
        private val tokenDataSource: TokenDataSource,
    ) : AuthRepository {
        override suspend fun loginWithKakao(kakaoToken: String): LoginResponse = authService.postAuthKakao(LoginRequest(kakaoToken))

        override suspend fun authRefresh(refreshToken: String): TokenResponse =
            authService.postAuthReissue(ReissueRequest(refreshToken = refreshToken))

        override suspend fun authLogout(): Result<Unit> =
            runCatching {
                authService.deleteAuthLogout()
            }.onSuccess {
                tokenDataSource.deleteAccessToken()
                tokenDataSource.deleteRefreshToken()
            }

        override suspend fun authWithdrawal(): Result<Unit> =
            runCatching {
                authService.deleteAuthWithdrawal()
            }.onSuccess {
                tokenDataSource.deleteAccessToken()
                tokenDataSource.deleteRefreshToken()
            }
    }
