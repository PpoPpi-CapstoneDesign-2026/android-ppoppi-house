package com.ppoppi.house.ui.diagnosis.result

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ppoppi.house.domain.model.Diagnosis
import com.ppoppi.house.domain.model.Pet
import com.ppoppi.house.domain.model.Triage
import com.ppoppi.house.ui.diagnosis.select.SelectActivity
import com.ppoppi.house.ui.main.MainActivity
import com.ppoppi.house.ui.main.navigation.MAP
import com.ppoppi.house.ui.theme.PpoPpiTheme

class ResultActivity : ComponentActivity() {
    val diagnosis =
        Diagnosis(
            hasDiagnosis = true,
            imageUrl = "",
            triage = Triage.URGENT,
            diseaseName = "결막염",
            affectedArea = "각막",
            triageConfidence = 80,
            guideAction = "asdfasdf",
            guideMessage = "일주일 내",
            guideWarning = "sdf",
            symptoms = emptyList(),
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
