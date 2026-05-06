package com.ppoppi.house.ui.onboarding.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ppoppi.house.domain.model.COLOR
import com.ppoppi.house.domain.model.Pet
import com.ppoppi.house.domain.model.SEX
import com.ppoppi.house.domain.model.SPECIES
import com.ppoppi.house.ui.main.MainActivity
import com.ppoppi.house.ui.onboarding.register.OnboardingRegisterActivity
import com.ppoppi.house.ui.theme.PpoPpiTheme

class OnboardingListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val pets: List<Pet> =
            listOf(
                Pet(
                    name = "asdf",
                    species = SPECIES.DOG,
                    breed = "푸들",
                    age = 2,
                    sex = SEX.FEMALE,
                    color = COLOR.PRIMARY200,
                ),
                Pet(
                    name = "asdf",
                    species = SPECIES.DOG,
                    breed = "푸들",
                    age = 2,
                    sex = SEX.FEMALE,
                    color = COLOR.PRIMARY100,
                ),
            )
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
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

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, OnboardingListActivity::class.java)
    }
}
