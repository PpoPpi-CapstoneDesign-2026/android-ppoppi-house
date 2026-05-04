package com.ppoppi.house.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ppoppi.house.domain.repository.SymptomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.HttpURLConnection
import javax.inject.Inject

@HiltViewModel
class SplashViewModel
    @Inject
    constructor(
        private val symptomRepository: SymptomRepository,
    ) : ViewModel() {
        private val _uiState = MutableStateFlow<SplashUiState>(SplashUiState.Loading)
        val uiState: StateFlow<SplashUiState> = _uiState

        init {
            checkAuthStatus()
        }

        private fun checkAuthStatus() {
            viewModelScope.launch {
                val minDelay = async { delay(MINIMUM_SPLASH_DURATION_MS) }
                val authResult =
                    async {
                        runCatching {
                            symptomRepository.getSymptoms()
                            SplashUiState.Authenticated
                        }.getOrElse { e ->
                            if (e is HttpException && e.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                                SplashUiState.Unauthenticated
                            } else {
                                SplashUiState.Unauthenticated
                            }
                        }
                    }
                minDelay.await()
                _uiState.value = authResult.await()
            }
        }

        companion object {
            private const val MINIMUM_SPLASH_DURATION_MS = 1500L
        }
    }

sealed class SplashUiState {
    object Loading : SplashUiState()

    object Authenticated : SplashUiState()

    object Unauthenticated : SplashUiState()
}
