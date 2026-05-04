package com.ppoppi.house.ui.diagnosis.check

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.content.IntentCompat
import com.ppoppi.house.domain.model.Pet
import com.ppoppi.house.ui.diagnosis.result.ResultActivity
import com.ppoppi.house.ui.theme.PpoPpiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChecklistActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val pet =
            intent.getParcelableExtra<Pet>(EXTRA_PET) ?: return finish()
        val imageUri =
            IntentCompat.getParcelableExtra(intent, EXTRA_IMAGE_URI, Uri::class.java)
                ?: return finish()

        setContent {
            PpoPpiTheme {
                ChecklistScreen(
                    onBackClick = { finish() },
                    onComplete = { checkedSymptoms ->
                        startActivity(ResultActivity.newIntent(this, pet, imageUri, checkedSymptoms))
                    },
                )
            }
        }
    }

    companion object {
        private const val EXTRA_IMAGE_URI: String = "EXTRA_IMAGE_URI"
        private const val EXTRA_PET: String = "EXTRA_PET"

        fun newIntent(
            context: Context,
            pet: Pet,
            imageUri: Uri,
        ): Intent =
            Intent(context, ChecklistActivity::class.java).apply {
                putExtra(EXTRA_IMAGE_URI, imageUri)
                putExtra(EXTRA_PET, pet)
            }
    }
}
