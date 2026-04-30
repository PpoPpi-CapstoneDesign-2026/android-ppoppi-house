package com.ppoppi.house.ui.diagnosis.result

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ppoppi.house.domain.Diagnosis
import com.ppoppi.house.domain.Pet
import com.ppoppi.house.domain.Triage
import com.ppoppi.house.ui.diagnosis.select.SelectActivity
import com.ppoppi.house.ui.main.MainActivity
import com.ppoppi.house.ui.main.navigation.MAP
import com.ppoppi.house.ui.theme.PpoPpiTheme

class ResultActivity : ComponentActivity() {
    val diagnosis =
        Diagnosis(
            guideTitle = "결막염",
            triageKey = Triage.MONITOR,
            triageConfidence = 70.0,
            affectedArea = "각막",
            guideMsg = "결막염이 의심 어쩌",
            guideAction = "일주일 내 내원 권장",
            imageUrl = null,
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val pet = intent.getParcelableExtra<Pet>(EXTRA_PET) ?: return
        val imageUri = intent.getParcelableExtra<Uri>(EXTRA_IMAGE_URI) ?: return
        val symptoms = intent.getStringArrayListExtra(EXTRA_SYMPTOMS) ?: emptyList<String>()

        // 여기서 증상 진단 API 요청

        setContent {
            PpoPpiTheme {
                ResultScreen(
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
                    imageUri = imageUri,
                    diagnosis = diagnosis,
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
            symptoms: List<String>,
        ): Intent =
            Intent(context, ResultActivity::class.java).apply {
                putExtra(EXTRA_PET, pet)
                putExtra(EXTRA_IMAGE_URI, imageUri)
                putStringArrayListExtra(EXTRA_SYMPTOMS, ArrayList(symptoms))
            }
    }
}
