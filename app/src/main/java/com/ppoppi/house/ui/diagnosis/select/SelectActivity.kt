package com.ppoppi.house.ui.diagnosis.select

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
import com.ppoppi.house.ui.diagnosis.camera.CameraActivity
import com.ppoppi.house.ui.theme.PpoPpiTheme

class SelectActivity : ComponentActivity() {
    val pets =
        listOf(
            Pet(
                name = "김은지",
                species = SPECIES.DOG,
                breed = "사람",
                age = 24,
                sex = SEX.FEMALE,
                color = COLOR.PRIMARY200,
            ),
            Pet(
                name = "김은지",
                species = SPECIES.DOG,
                breed = "사람",
                age = 24,
                sex = SEX.FEMALE,
                color = COLOR.PRIMARY50,
            ),
            Pet(
                name = "김은지",
                species = SPECIES.DOG,
                breed = "사람",
                age = 24,
                sex = SEX.FEMALE,
                color = COLOR.PRIMARY600,
            ),
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
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

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, SelectActivity::class.java)
    }
}
