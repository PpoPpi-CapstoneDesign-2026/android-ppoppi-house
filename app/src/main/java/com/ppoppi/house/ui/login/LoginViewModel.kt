package com.ppoppi.house.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ppoppi.house.data.datasource.TokenDataSource
import com.ppoppi.house.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
    @Inject
    constructor(
        private val loginRepository: AuthRepository,
        private val tokenDataSource: TokenDataSource,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
        val uiState: StateFlow<LoginUiState> = _uiState

        fun loginWithKakao(kakaoToken: String) {
            viewModelScope.launch {
                _uiState.value = LoginUiState.Loading
                _uiState.value =
                    runCatching {
                        val response = loginRepository.loginWithKakao(kakaoToken)
                        tokenDataSource.setAccessToken(response.accessToken)
                        tokenDataSource.setRefreshToken(response.refreshToken)
                        LoginUiState.Success(isNewUser = response.isOnboarding)
                    }.getOrElse { e ->
                        LoginUiState.Error(e.message ?: "로그인 중 오류가 발생했어요")
                    }
            }
        }
    }

sealed class LoginUiState {
    object Idle : LoginUiState()

    object Loading : LoginUiState()

    data class Success(
        val isNewUser: Boolean,
    ) : LoginUiState()

    data class Error(
        val message: String,
    ) : LoginUiState()
}
