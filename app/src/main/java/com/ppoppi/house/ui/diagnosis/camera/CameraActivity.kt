package com.ppoppi.house.ui.diagnosis.camera

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.content.IntentCompat
import com.ppoppi.house.domain.Pet
import com.ppoppi.house.ui.diagnosis.check.ChecklistActivity
import com.ppoppi.house.ui.theme.PpoPpiTheme

class CameraActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val pet = IntentCompat.getParcelableExtra(intent, EXTRA_PET, Pet::class.java) ?: throw IllegalArgumentException()

        setContent {
            PpoPpiTheme {
                CameraScreen(
                    onBackClick = { finish() },
                    onCaptured = { uri ->
                        startActivity(ChecklistActivity.newIntent(this, pet, uri))
                    },
                    name = pet.name,
                )
            }
        }
    }

    companion object {
        private const val EXTRA_PET: String = "EXTRA_PET"

        fun newIntent(
            context: Context,
            pet: Pet,
        ): Intent =
            Intent(context, CameraActivity::class.java).apply {
                putExtra(EXTRA_PET, pet)
            }
    }
}
