package com.ppoppi.house.ui.onboarding.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ppoppi.house.R
import com.ppoppi.house.domain.COLOR
import com.ppoppi.house.domain.Pet
import com.ppoppi.house.domain.SEX
import com.ppoppi.house.domain.SPECIES
import com.ppoppi.house.ui.component.BottomBarButton
import com.ppoppi.house.ui.onboarding.register.component.AgeInputSection
import com.ppoppi.house.ui.onboarding.register.component.BreedInputSection
import com.ppoppi.house.ui.onboarding.register.component.ColorInputSection
import com.ppoppi.house.ui.onboarding.register.component.NameInputSection
import com.ppoppi.house.ui.onboarding.register.component.PetRegisterTopAppBar
import com.ppoppi.house.ui.onboarding.register.component.SexInputSection
import com.ppoppi.house.ui.onboarding.register.component.SpeciesInputSection
import com.ppoppi.house.ui.theme.PpoPpiTheme
import com.ppoppi.house.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetRegisterScreen(
    onComplete: (pet: Pet) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    pet: Pet? = null,
) {
    var name by rememberSaveable { mutableStateOf(pet?.name ?: "") }
    var species by rememberSaveable { mutableStateOf(pet?.species ?: SPECIES.DOG) }
    var breed: String? by rememberSaveable { mutableStateOf(pet?.breed) }
    var sex by rememberSaveable { mutableStateOf(pet?.sex ?: SEX.MALE) }
    var age by rememberSaveable { mutableIntStateOf(pet?.age ?: 1) }
    var color by rememberSaveable { mutableIntStateOf(pet?.color?.ordinal ?: 1) }

    Scaffold(
        topBar = { PetRegisterTopAppBar(onBackClick) },
        modifier =
            modifier
                .background(White),
        containerColor = White,
        bottomBar = {
            BottomBarButton(
                onClick = {
                    onComplete(
                        Pet(
                            name = name,
                            species = species,
                            breed = breed ?: "말티즈",
                            age = age,
                            sex = sex,
                            color = COLOR.entries[color],
                        ),
                    )
                },
                text = stringResource(R.string.pet_register_bottom_bar_button),
            )
        },
    ) { innerPadding ->

        Column(
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 30.dp)
                    .verticalScroll(rememberScrollState()),
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            // 이름 입력 섹션
            NameInputSection(
                name = name,
                onValueChanged = { name = it },
            )

            Spacer(modifier = Modifier.height(30.dp))

            // 종류 입력 섹션
            SpeciesInputSection(
                species = species,
                onValueChanged = { species = it },
            )

            Spacer(modifier = Modifier.height(30.dp))

            // 품종 입력 섹션
            BreedInputSection(
                breed = breed,
                onValueChanged = { breed = it },
            )

            Spacer(modifier = Modifier.height(30.dp))

            // 성별 입력 섹션
            SexInputSection(
                sex = sex,
                onValueChanged = { sex = it },
            )

            Spacer(modifier = Modifier.height(30.dp))

            // 나이 입력 섹션
            AgeInputSection(
                onDecreaseAge = { age-- },
                onIncreaseAge = { age++ },
                age = age,
            )

            Spacer(modifier = Modifier.height(30.dp))

            // 색상 선택 섹션
            ColorInputSection(
                color = color,
                onValueChanged = { color = it },
            )

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PetRegisterScreenPreview() {
    PpoPpiTheme {
        PetRegisterScreen(onComplete = {}, onBackClick = {})
    }
}
