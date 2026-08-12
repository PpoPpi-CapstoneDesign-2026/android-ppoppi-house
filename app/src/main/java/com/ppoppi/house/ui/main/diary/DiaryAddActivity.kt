package com.ppoppi.house.ui.main.diary

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ppoppi.house.ui.theme.PpoPpiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiaryAddActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val petId = intent.getLongExtra(EXTRA_PET_ID, -1L)
        val petName = intent.getStringExtra(EXTRA_PET_NAME).orEmpty()
        setContent {
            PpoPpiTheme {
                DiaryAddScreen(
                    onBackClick = { finish() },
                    onSaveSuccess = { savedPetId, savedPetName, memo, selectedChecklist ->
                        val resultIntent =
                            Intent().apply {
                                putExtra(RESULT_PET_ID, savedPetId)
                                putExtra(RESULT_PET_NAME, savedPetName)
                                putExtra(RESULT_MEMO, memo)
                                putExtra(RESULT_CHECK_IDS, selectedChecklist.map { it.id }.toLongArray())
                                putExtra(RESULT_CHECK_NAMES, selectedChecklist.map { it.description }.toTypedArray())
                            }
                        setResult(RESULT_OK, resultIntent)
                        finish()
                    },
                    petId = petId,
                    petName = petName,
                )
            }
        }
    }

    companion object {
        private const val EXTRA_PET_ID = "EXTRA_PET_ID"
        private const val EXTRA_PET_NAME = "EXTRA_PET_NAME"

        const val RESULT_PET_ID = "RESULT_PET_ID"
        const val RESULT_PET_NAME = "RESULT_PET_NAME"
        const val RESULT_MEMO = "RESULT_MEMO"
        const val RESULT_CHECK_IDS = "RESULT_CHECK_IDS"
        const val RESULT_CHECK_NAMES = "RESULT_CHECK_NAMES"

        fun newIntent(
            context: Context,
            petId: Long,
            petName: String,
        ): Intent =
            Intent(context, DiaryAddActivity::class.java).apply {
                putExtra(EXTRA_PET_ID, petId)
                putExtra(EXTRA_PET_NAME, petName)
            }
    }
}
