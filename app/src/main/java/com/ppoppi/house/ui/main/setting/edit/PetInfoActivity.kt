package com.ppoppi.house.ui.main.setting.edit

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
class PetInfoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val pet = intent.getParcelableExtra<Pet>(EXTRA_PET)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PpoPpiTheme {
                PetInfoScreen(
                    onRegistered = { finish() },
                    onBackClick = { finish() },
                    pet = pet,
                )
            }
        }
    }

    companion object Companion {
        private const val EXTRA_PET: String = "EXTRA_PET"

        fun newIntent(
            context: Context,
            pet: Pet,
        ): Intent =
            Intent(context, PetInfoActivity::class.java).apply {
                putExtra(EXTRA_PET, pet)
            }

        fun newIntent(context: Context): Intent = Intent(context, PetInfoActivity::class.java)
    }
}
