package com.ppoppi.house.ui.onboarding.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ppoppi.house.domain.model.Pet
import com.ppoppi.house.ui.theme.PpoPpiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingRegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val pet = intent.getParcelableExtra<Pet>(EXTRA_PET)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PpoPpiTheme {
                PetRegisterScreen(
                    onRegistered = { finish() },
                    onBackClick = { finish() },
                    pet = pet,
                )
            }
        }
    }

    companion object {
        private const val EXTRA_PET: String = "EXTRA_PET"

        fun newIntent(context: Context): Intent = Intent(context, OnboardingRegisterActivity::class.java)

        fun newIntent(
            context: Context,
            pet: Pet,
        ): Intent =
            Intent(context, OnboardingRegisterActivity::class.java).apply {
                putExtra(EXTRA_PET, pet)
            }
    }
}
