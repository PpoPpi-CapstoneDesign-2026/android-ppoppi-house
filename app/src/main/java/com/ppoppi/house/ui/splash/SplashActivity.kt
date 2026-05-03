package com.ppoppi.house.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.ppoppi.house.ui.login.LoginActivity
import com.ppoppi.house.ui.main.MainActivity
import com.ppoppi.house.ui.theme.PpoPpiTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PpoPpiTheme {
                SplashScreen()
            }
        }
        observeUiState()
    }

    private fun observeUiState() {
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    SplashUiState.Authenticated -> navigateToMain()
                    SplashUiState.Unauthenticated -> navigateToLogin()
                    SplashUiState.Loading -> Unit
                }
            }
        }
    }

    private fun navigateToMain() {
        startActivity(
            MainActivity.newIntent(this).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            },
        )
    }

    private fun navigateToLogin() {
        startActivity(
            Intent(this, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            },
        )
    }
}
