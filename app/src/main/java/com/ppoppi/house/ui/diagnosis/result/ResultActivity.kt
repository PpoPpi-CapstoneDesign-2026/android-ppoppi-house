package com.ppoppi.house.ui.diagnosis.result

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ppoppi.house.domain.model.Pet
import com.ppoppi.house.ui.diagnosis.select.SelectActivity
import com.ppoppi.house.ui.main.MainActivity
import com.ppoppi.house.ui.main.navigation.MAP
import com.ppoppi.house.ui.theme.PpoPpiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultActivity : ComponentActivity() {
    private val viewModel: ResultViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val pet = intent.getParcelableExtra<Pet>(EXTRA_PET) ?: return
        val imageUri = intent.getParcelableExtra<Uri>(EXTRA_IMAGE_URI) ?: return
        val symptoms = intent.getIntegerArrayListExtra(EXTRA_SYMPTOMS) ?: emptyList<Int>()
        val petId = pet.id ?: return

        viewModel.diagnose(petId, symptoms, imageUri)

        setContent {
            val uiState by viewModel.uiState.collectAsState()
            PpoPpiTheme {
                ResultScreen(
                    petName = pet.name,
                    uiState = uiState,
                    imageUri = imageUri,
                    navigateToHome = {
                        startActivity(
                            MainActivity.newIntent(this@ResultActivity).apply {
                                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            },
                        )
                    },
                    navigateToMap = {
                        startActivity(
                            MainActivity.newIntent(this@ResultActivity, MAP).apply {
                                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            },
                        )
                    },
                    navigateToDiagnosis = {
                        startActivity(SelectActivity.newIntent(this@ResultActivity))
                        finish()
                    },
                )
            }
        }
    }

    companion object {
        private const val EXTRA_PET: String = "EXTRA_PET"
        private const val EXTRA_SYMPTOMS: String = "EXTRA_SYMPTOMS"
        private const val EXTRA_IMAGE_URI: String = "EXTRA_IMAGE_URI"

        fun newIntent(
            context: Context,
            pet: Pet,
            imageUri: Uri,
            symptoms: List<Int>,
        ): Intent =
            Intent(context, ResultActivity::class.java).apply {
                putExtra(EXTRA_PET, pet)
                putExtra(EXTRA_IMAGE_URI, imageUri)
                putIntegerArrayListExtra(EXTRA_SYMPTOMS, ArrayList(symptoms))
            }
    }
}
