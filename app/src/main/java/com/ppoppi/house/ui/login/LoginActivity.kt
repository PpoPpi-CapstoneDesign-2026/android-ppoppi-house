package com.ppoppi.house.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            }
                        startActivity(intent)
                    },
                    toOnboarding = {
                        val intent =
                            OnboardingListActivity.newIntent(this).apply {
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            }
                        startActivity(intent)
                    },
                )
            }
        }
    }
}
