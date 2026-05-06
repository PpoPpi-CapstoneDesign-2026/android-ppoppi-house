package com.ppoppi.house.ui.main.diary

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ppoppi.house.ui.theme.PpoPpiTheme

class DiaryAddActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PpoPpiTheme {
                DiaryAddScreen(
                    onBackClick = { finish() },
                )
            }
        }
    }

    companion object {
        private const val EXTRA_PET_ID: String = "EXTRA_PET_ID"

        fun newIntent(
            context: Context,
            petId: Long,
        ): Intent =
            Intent(context, DiaryAddActivity::class.java).apply {
                putExtra(EXTRA_PET_ID, petId)
            }
    }
}
