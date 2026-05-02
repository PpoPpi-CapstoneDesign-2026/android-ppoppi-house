package com.ppoppi.house.data.repository

import com.ppoppi.house.data.model.LoginRequest
import com.ppoppi.house.data.model.LoginResponse
import com.ppoppi.house.data.service.LoginApiService
import com.ppoppi.house.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginApiService: LoginApiService,
) : LoginRepository {
    override suspend fun loginWithKakao(kakaoToken: String): LoginResponse =
        loginApiService.loginWithKakao(LoginRequest(kakaoToken))
}
