package com.ppoppi.house.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.ppoppi.house.ui.main.MainActivity
import com.ppoppi.house.ui.onboarding.list.OnboardingListActivity
import com.ppoppi.house.ui.theme.PpoPpiTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PpoPpiTheme {
                LoginScreen(
                    toMain = {
                        val intent =
                            MainActivity.newIntent(this).apply {
                                flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            }
                        startActivity(intent)
                    },
                    toOnboarding = {
                        val intent =
                            OnboardingListActivity.newIntent(this).apply {
                                flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            }
                        startActivity(intent)
                    },
                    onKakaoLogin = {
                        loginWithKakao(
                            onSuccess = {
                                Log.d("hwannow", "success")
                                val intent =
                                    MainActivity.newIntent(this).apply {
                                        flags =
                                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    }
                                startActivity(intent)
                            },
                            onError = {
                                Toast.makeText(this, "인터넷 연결을 확인해 주세요", Toast.LENGTH_SHORT).show()
                                Log.d("hwannow", it)
                            }
                        )
                    }
                )
            }
        }
    }

    private fun loginWithKakao(
        onSuccess: (token: String) -> Unit,
        onError: (errorMessage: String) -> Unit,
    ) {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            loginWithKakaoTalk(onSuccess, onError)
        } else {
            loginWithKakaoAccount(onSuccess, onError)
        }
    }

    private fun loginWithKakaoTalk(
        onSuccess: (token: String) -> Unit,
        onError: (errorMessage: String) -> Unit,
    ) {
        UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
            when {
                error is ClientError && error.reason == ClientErrorCause.Cancelled -> {
                    Unit
                }

                error != null -> {
                    loginWithKakaoAccount(onSuccess, onError)
                }

                else -> {
                    token?.let {
                        onSuccess(it.accessToken)
                    }
                }
            }
        }
    }

    private fun loginWithKakaoAccount(
        onSuccess: (token: String) -> Unit,
        onError: (errorMessage: String) -> Unit,
    ) {
        UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
            if (error != null) {
                onError(error.message ?: "Login Error")
            } else {
                token?.let {
                    onSuccess(it.accessToken)
                }
            }
        }
    }
}
