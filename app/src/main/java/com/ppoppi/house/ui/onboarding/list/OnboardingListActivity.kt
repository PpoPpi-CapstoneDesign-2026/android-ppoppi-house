package com.ppoppi.house.ui.onboarding.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ppoppi.house.ui.main.MainActivity
import com.ppoppi.house.ui.onboarding.register.OnboardingRegisterActivity
import com.ppoppi.house.ui.theme.PpoPpiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingListActivity : ComponentActivity() {
    private val viewModel: OnboardingListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val pets by viewModel.pets.collectAsState()
            PpoPpiTheme {
                PetListScreen(
                    onStart = {
                        val intent = MainActivity.newIntent(this)
                        startActivity(intent)
                    },
                    onEdit = { pet ->
                        val intent = OnboardingRegisterActivity.newIntent(this, pet)
                        startActivity(intent)
                    },
                    onRegister = {
                        val intent = OnboardingRegisterActivity.newIntent(this)
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
        fun newIntent(context: Context): Intent = Intent(context, OnboardingListActivity::class.java)
    }
}
