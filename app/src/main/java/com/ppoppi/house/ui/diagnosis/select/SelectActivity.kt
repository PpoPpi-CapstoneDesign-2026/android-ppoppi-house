package com.ppoppi.house.ui.diagnosis.select

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ppoppi.house.ui.diagnosis.camera.CameraActivity
import com.ppoppi.house.ui.theme.PpoPpiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectActivity : ComponentActivity() {
    private val viewModel: PetSelectViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val pets by viewModel.pets.collectAsState()
            PpoPpiTheme {
                PetSelectScreen(
                    onBackClick = { finish() },
                    onSelect = { pet ->
                        val intent = CameraActivity.newIntent(this, pet)
                        startActivity(intent)
                    },
                    pets = pets,
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getPets()
    }

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, SelectActivity::class.java)
    }
}
